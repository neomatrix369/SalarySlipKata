package SalarySlipKata.domain_service;

import static java.lang.Integer.MAX_VALUE;
import static SalarySlipKata.domain.GBP.zero;

import java.util.ArrayList;
import java.util.List;

import SalarySlipKata.domain.GBP;

public class NationalInsuranceCalculatorService {
  private static final int TWELVE_MONTHS = 12;

  private static GBP monthly(double amount) {
    return new GBP(amount).dividedBy(TWELVE_MONTHS);
  }

  private static final List<SlabRange> contributionSlabs = new ArrayList<SlabRange>()
  {
    { add(new SlabRange(zero(),            monthly(8060.00),   0.0)); }
    { add(new SlabRange(monthly(8060.00),  monthly(43000.00), 12.0)); }
    { add(new SlabRange(monthly(43000.00), monthly(MAX_VALUE), 2.0)); }
  };

  private final List<SlabRate> salarySlabsThatApply = new ArrayList<>();

  public GBP calculate(GBP basicSalary) {
    for (SlabRange slabRange: contributionSlabs) {
      final GBP salaryDifferenceFromSlab = basicSalary.differenceFrom(slabRange.higherRange);
      if (salaryDifferenceFromSlab.isGreaterThanZero()) {
        final GBP slabDifference = slabRange.higherRange.minus(slabRange.lowerRange);
        salarySlabsThatApply.add(new SlabRate(slabDifference, slabRange.rate));
      } else {
        salarySlabsThatApply.add(new SlabRate(salaryDifferenceFromSlab, slabRange.rate));
      }
    }

    GBP total = new GBP(0.0);
    for (SlabRate slabRate: salarySlabsThatApply) {
      total = total.plus(slabRate.amount.multiplyBy(slabRate.rate));
    }

    return total;
  }

  private static class SlabRate {
    private GBP amount;
    private double rate;

    private SlabRate(GBP amount, double rate) {
      this.amount = amount;
      this.rate = rate;
    }
  }

  private static class SlabRange {
    private GBP lowerRange;
    private GBP higherRange;
    private double rate;

    private SlabRange(GBP lowerRange, GBP higherRange, double rate) {
      this.lowerRange = lowerRange;
      this.higherRange = higherRange;
      this.rate = rate;
    }
  }
}
