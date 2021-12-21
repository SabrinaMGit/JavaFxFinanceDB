package Activitys;


import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class Formulas {

    private JdbcDao jdbcDao;
    DateFormat df;

    private java.sql.Date effectiveDate;
    public java.sql.Date expireDate;
    private java.sql.Date refundDate;
    public java.sql.Date cancellationDate;

    private int effectiveDateInt;
    private int effectiveDateYear;
    private int effectiveDateMonth;
    private int effectiveDateDay;

    private java.sql.Date date_2016_10_1;
    private java.sql.Date date_2017_6_1;
    public java.sql.Date settlementPeriod_startDate;
    public java.sql.Date settlementPeriod_endDate;
    public java.sql.Date date_0;

    private Double p17_5 = 17.5;
    private Double p6_5 = 6.5;
    private Double p8_5 = 8.5;
    private Double p2_5 = 2.5;
    private Double p11_0 = 11.0;
    private Double v1_1 = 1.1;
    private Double v1_12 = 1.12;
    private Double v1_095 = 1.095;
    private Double v0_0 = 0.0;
    private int daysPerYear = 365;

    private int accountingPaymentTotal;
    private int accountingPaymentBegin;
    private int accountingPaymentPeriod;
    private double accountingPaymentRefund;

    //List<Date>[] dateFromColumn = new ArrayList<Date>().toArray(new List[0]);
    ArrayList<Date> dateFromColumns=new ArrayList<>();
    private Date date = null;

    public Double totalYearlyPremiumIncl_Tax;
    public Double totalYearlyPremiumNet = 0.0;
    private Double dailyPremiumNet;
    private Double dailyPremiumInclTax;
    private Double totalCommissionPerYear17_5;
    private Double mirasconCommissionPerYear6_5;
    private Double geicoGmbhCommissionPerYear11;
    private int ofInstallmentsPerYear;
    private String modeOfPayments;
    public Double premiumInclTaxPerInstallment;
    public Double netPremiumPerInstallment = 0.0;
    private Double ersProductionNetSettlementPeriod;
    private Double refundNet;
    private Double erstProductiveInclTaxSettlementPeriod;
    private Double ersProductionNetTotal;
    private Double ersProductionInclTaxTotal;
    private int ofPaidInstallmentsYTD;
    private int ofUnpaidInstallmentsYTD;
    private Double premiumInclTaxPaidYTD;
    private Double netPremiumPaidYTD;
    private Double total_17_5_payable;
    private Double premiumInclTaxPaid;
    private Double regisMirasconPayableNet;
    private Double total_17_5_return;
    private Double total_17_5_playable_return;
    private Double mirascon_6_5_payable;
    private Double mirasconToGeicoGmbh_11_payable;
    private Double disbursementGeicoCommission_GeicoUkAgency_8_5;
    private Double disbursementGeicoCommision_GeicoGmbh_2_5;
    private Double totalGeicoCommisionYTD;
    private Double regis_MirasconToRegisTGSL_playable_return;
    public Double cancellationsRefundGross;
    private Double cancellations_RegisTGSLtoMirascon_payableNet;
    private Double cancellations_RegisTGSLtoMirascon_payableGross;
    private Double pre_financingTool_GeicoFinancialToMirasconUpfront_payment_payable;
    private Double pre_financingTool_GeicoFinancialToMirasconUpfront_paymentTotalReceived;
    private Double pre_financingTool_MirasconToGeicoFinancial_payable;
    private Double pre_financingTool_MirasconToGeico_financialPaid;
    private Double pre_financingTool_MirasconToGeicoFinancialRefund_MTA;
    private Double pre_financingTool_MirasconToGeicoFinancialResidualDept;

    //values of sums
    private double totalYearlyPremiumNet_Add;
    public double totalYearlyPremiumNet_Sum;

    //All ADD values for sum
    private Double totalCommissionPerYear17_5_Add;
    private Double mirasconCommissionPerYear6_5_Add;
    private Double geicoGmbhCommissionPerYear11_Add;
    public Double premiumInclTaxPerInstallment_Add;
    private Double ersProductionNetSettlementPeriod_Add;
    private Double erstProductiveInclTaxSettlementPeriod_Add;
    private Double ersProductionNetTotal_Add;
    private Double ersProductionInclTaxTotal_Add;
    private Double premiumInclTaxPaidYTD_Add;
    private Double netPremiumPaidYTD_Add;
    private Double total_17_5_payable_Add;
    private Double premiumInclTaxPaid_Add;
    private Double regisMirasconPayableNet_Add;
    private Double total_17_5_return_Add;
    private Double total_17_5_playable_return_Add;
    private Double mirascon_6_5_payable_Add;
    private Double mirasconToGeicoGmbh_11_payable_Add;
    private Double disbursementGeicoCommission_GeicoUkAgency_8_5_Add;
    private Double disbursementGeicoCommision_GeicoGmbh_2_5_Add;
    private Double totalGeicoCommisionYTD_Add;
    private Double regis_MirasconToRegisTGSL_playable_return_Add;
    private Double cancellations_RegisTGSLtoMirascon_payableNet_Add;
    private Double cancellations_RegisTGSLtoMirascon_payableGross_Add;
    private Double pre_financingTool_GeicoFinancialToMirasconUpfront_payment_payable_Add;
    private Double pre_financingTool_GeicoFinancialToMirasconUpfront_paymentTotalReceived_Add;
    private Double pre_financingTool_MirasconToGeicoFinancial_payable_Add;
    private Double pre_financingTool_MirasconToGeico_financialPaid_Add;
    private Double pre_financingTool_MirasconToGeicoFinancialRefund_MTA_Add;
    private Double pre_financingTool_MirasconToGeicoFinancialResidualDept_Add;

    //All SUM Values for sum
    public Double totalCommissionPerYear17_5_Sum = 0.0;
    public Double mirasconCommissionPerYear6_5_Sum = 0.0;
    public Double geicoGmbhCommissionPerYear11_Sum = 0.0;
    public Double premiumInclTaxPerInstallment_Sum = 0.0;
    public Double ersProductionNetSettlementPeriod_Sum = 0.0;
    public Double erstProductiveInclTaxSettlementPeriod_Sum = 0.0;
    public Double ersProductionNetTotal_Sum = 0.0;
    public Double ersProductionInclTaxTotal_Sum = 0.0;
    public Double premiumInclTaxPaidYTD_Sum = 0.0;
    public Double netPremiumPaidYTD_Sum = 0.0;
    public Double total_17_5_payable_Sum = 0.0;
    public Double premiumInclTaxPaid_Sum = 0.0;
    public Double regisMirasconPayableNet_Sum = 0.0;
    public Double total_17_5_return_Sum = 0.0;
    public Double total_17_5_playable_return_Sum = 0.0;
    public Double mirascon_6_5_payable_Sum = 0.0;
    public Double mirasconToGeicoGmbh_11_payable_Sum = 0.0;
    public Double disbursementGeicoCommission_GeicoUkAgency_8_5_Sum = 0.0;
    public Double disbursementGeicoCommision_GeicoGmbh_2_5_Sum = 0.0;
    public Double totalGeicoCommisionYTD_Sum = 0.0;
    public Double regis_MirasconToRegisTGSL_playable_return_Sum = 0.0;
    public Double cancellations_RegisTGSLtoMirascon_payableNet_Sum = 0.0;
    public Double cancellations_RegisTGSLtoMirascon_payableGross_Sum = 0.0;
    public Double pre_financingTool_GeicoFinancialToMirasconUpfront_payment_payable_Sum = 0.0;
    public Double pre_financingTool_GeicoFinancialToMirasconUpfront_paymentTotalReceived_Sum = 0.0;
    public Double pre_financingTool_MirasconToGeicoFinancial_payable_Sum = 0.0;
    public Double pre_financingTool_MirasconToGeico_financialPaid_Sum = 0.0;
    public Double pre_financingTool_MirasconToGeicoFinancialRefund_MTA_Sum = 0.0;
    public Double pre_financingTool_MirasconToGeicoFinancialResidualDept_Sum = 0.0;


    public void formulas() throws ParseException, SQLException {
        System.out.println("----------------------Start Formulas Class------------------------");
        //aufrunden und abrunden, Summenfunktion (Summenformel für datenbanken)
        jdbcDao = new JdbcDao();
        effectiveDate = jdbcDao.selectColumnDateValue_WithID(1, 5);

        df = new SimpleDateFormat("yyyy-MM-dd");
        date_2016_10_1 = new java.sql.Date(df.parse("2016-10-01").getTime());
        date_2017_6_1 = new java.sql.Date(df.parse("2017-06-01").getTime());
        settlementPeriod_startDate = new java.sql.Date(df.parse("2018-10-01").getTime());
        settlementPeriod_endDate = new java.sql.Date(df.parse("2018-10-30").getTime());
        date_0 = new java.sql.Date(df.parse("0000-00-00").getTime());
        System.out.println("Date_O Check If Zero : "+date_0);

        effectiveDate = new java.sql.Date(df.parse("" + effectiveDate).getTime());

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(effectiveDate);
        effectiveDateMonth = calendar.get(Calendar.MONTH) + 1;
        System.out.println("Effective Month = " + effectiveDateMonth);
        effectiveDateDay = calendar.get(Calendar.DAY_OF_MONTH);
        System.out.println("Effective Day = " + effectiveDateDay);
        effectiveDateYear = calendar.get(Calendar.YEAR);
        System.out.println("Effective Year = " + effectiveDateYear);

        //effectiveDate = new java.sql.Date(df.parse(""+jdbcDao.selectCustomer(1,4)).getTime());
        //effectiveDateInt = Integer.valueOf(df.format(String.valueOf(effectiveDate)));

        //185 Refund Date Update to 0 Date
        /*for(int id = 185; id<=201;id++){
            jdbcDao.updateColumnDateValue_WithIdAndValue(id,39, null);
        }*/
        //run Accounting
        //AccountingFormulas accountingFormulas = new AccountingFormulas();
        //accountingFormulas.accountingFormulas();
        for(int id = 1; id <= 201; id++){ //201
            getTheSmallest(id);
            //run all methods
            expireDate(id);

            totalYearlyPremiumNet_Add = totalYearlyPremiumNet(id);
            totalYearlyPremiumNet_Sum += totalYearlyPremiumNet_Add;
            totalYearlyPremiumNet_Sum = Math.round(totalYearlyPremiumNet_Sum*100.0)/100.0;
            System.out.println("--This is the first sum of a column. Check it if it is correct! = "+totalYearlyPremiumNet_Sum);

            setMathRoundInColumn(id);
            dailyPremiumNet(id);
            dailyPremiumInclTax(id);

            totalCommissionPerYear17_5_Add = totalCommissionPerYear17_5(id);
            totalCommissionPerYear17_5_Sum += totalCommissionPerYear17_5_Add;
            totalCommissionPerYear17_5_Sum = Math.round(totalCommissionPerYear17_5_Sum*100.0)/100.0;

            mirasconCommissionPerYear6_5_Add = mirasconCommissionPerYear6_5(id);
            mirasconCommissionPerYear6_5_Sum += mirasconCommissionPerYear6_5_Add;
            mirasconCommissionPerYear6_5_Sum = Math.round(mirasconCommissionPerYear6_5_Sum*100.0)/100.0;

            geicoGmbhCommissionPerYear11_Add = geicoGmbhCommissionPerYear11(id);
            geicoGmbhCommissionPerYear11_Sum += geicoGmbhCommissionPerYear11_Add;
            geicoGmbhCommissionPerYear11_Sum = Math.round(geicoGmbhCommissionPerYear11_Sum*100.0)/100.0;

            ofInstallmentsPerYear(id);

            premiumInclTaxPerInstallment_Add = premiumInclTaxPerInstallment(id);
            premiumInclTaxPerInstallment_Sum += premiumInclTaxPerInstallment_Add;
            premiumInclTaxPerInstallment_Sum = Math.round(premiumInclTaxPerInstallment_Sum *100.0)/100.0;

            netPremiumPerInstallment(id);

            ersProductionNetSettlementPeriod_Add = ersProductionNetSettlementPeriod(id);
            ersProductionNetSettlementPeriod_Sum += ersProductionNetSettlementPeriod_Add;
            ersProductionNetSettlementPeriod_Sum = Math.round(ersProductionNetSettlementPeriod_Sum*100.0)/100.0;


            //.....
            erstProductiveInclTaxSettlementPeriod_Add = erstProductiveInclTaxSettlementPeriod(id);
            erstProductiveInclTaxSettlementPeriod_Sum += erstProductiveInclTaxSettlementPeriod_Add;
            erstProductiveInclTaxSettlementPeriod_Sum = Math.round(erstProductiveInclTaxSettlementPeriod_Sum*100.0)/100.0;

            ersProductionNetTotal_Add = ersProductionNetTotal(id);
            ersProductionNetTotal_Sum +=  ersProductionNetTotal_Add;
            ersProductionNetTotal_Sum = Math.round(ersProductionNetTotal_Sum*100.0)/100.0;

            ersProductionInclTaxTotal_Add = ersProductionInclTaxTotal(id);
            ersProductionInclTaxTotal_Sum += geicoGmbhCommissionPerYear11_Add;
            ersProductionInclTaxTotal_Sum = Math.round(ersProductionInclTaxTotal_Sum*100.0)/100.0;

            ofPaidInstallmentsYTD(id);
            ofUnpaidInstallmentsYTD(id);
            premiumInclTaxPaidYTD_Add = premiumInclTaxPaidYTD(id);
            premiumInclTaxPaidYTD_Sum += premiumInclTaxPaidYTD_Add;
            premiumInclTaxPaidYTD_Sum = Math.round(premiumInclTaxPaidYTD_Sum*100.0)/100.0;

            netPremiumPaidYTD_Add = netPremiumPaidYTD(id);
            netPremiumPaidYTD_Sum += netPremiumPaidYTD_Add;
            netPremiumPaidYTD_Sum = Math.round(netPremiumPaidYTD_Sum*100.0)/100.0;

            total_17_5_payable_Add = total_17_5_payable(id);
            total_17_5_payable_Sum += total_17_5_payable_Add;
            total_17_5_payable_Sum = Math.round(total_17_5_payable_Sum*100.0)/100.0;

            premiumInclTaxPaid_Add = premiumInclTaxPaid(id);
            premiumInclTaxPaid_Sum += premiumInclTaxPaid_Add;
            premiumInclTaxPaid_Sum = Math.round(premiumInclTaxPaid_Sum*100.0)/100.0;

            total_17_5_return_Add = total_17_5_return(id);
            total_17_5_return_Sum += total_17_5_return_Add;
            total_17_5_return_Sum = Math.round(total_17_5_return_Sum*100.0)/100.0;

            total_17_5_playable_return_Add = total_17_5_playable_return(id);
            total_17_5_playable_return_Sum += total_17_5_playable_return_Add;
            total_17_5_playable_return_Sum = Math.round(total_17_5_playable_return_Sum*100.0)/100.0;

            mirascon_6_5_payable_Add = mirascon_6_5_payable(id);
            mirascon_6_5_payable_Sum += mirascon_6_5_payable_Add;
            mirascon_6_5_payable_Sum = Math.round(mirascon_6_5_payable_Sum*100.0)/100.0;

            mirasconToGeicoGmbh_11_payable_Add = mirasconToGeicoGmbh_11_payable(id);
            mirasconToGeicoGmbh_11_payable_Sum += mirasconToGeicoGmbh_11_payable_Add;
            mirasconToGeicoGmbh_11_payable_Sum = Math.round(mirasconToGeicoGmbh_11_payable_Sum*100.0)/100.0;

            disbursementGeicoCommission_GeicoUkAgency_8_5_Add = disbursementGeicoCommission_GeicoUkAgency_8_5(id);
            disbursementGeicoCommission_GeicoUkAgency_8_5_Sum += disbursementGeicoCommission_GeicoUkAgency_8_5_Add;
            disbursementGeicoCommission_GeicoUkAgency_8_5_Sum = Math.round(disbursementGeicoCommission_GeicoUkAgency_8_5_Sum*100.0)/100.0;

            disbursementGeicoCommision_GeicoGmbh_2_5_Add = disbursementGeicoCommision_GeicoGmbh_2_5(id);
            disbursementGeicoCommision_GeicoGmbh_2_5_Sum += disbursementGeicoCommision_GeicoGmbh_2_5_Add;
            disbursementGeicoCommision_GeicoGmbh_2_5_Sum = Math.round(disbursementGeicoCommision_GeicoGmbh_2_5_Sum*100.0)/100.0;

            totalGeicoCommisionYTD_Add = totalGeicoCommisionYTD(id);
            totalGeicoCommisionYTD_Sum += totalGeicoCommisionYTD_Add;
            totalGeicoCommisionYTD_Sum = Math.round(totalGeicoCommisionYTD_Sum*100.0)/100.0;

            regis_MirasconToRegisTGSL_playable_return_Add = regis_MirasconToRegisTGSL_playable_return(id);
            regis_MirasconToRegisTGSL_playable_return_Sum += regis_MirasconToRegisTGSL_playable_return_Add;
            regis_MirasconToRegisTGSL_playable_return_Sum = Math.round(regis_MirasconToRegisTGSL_playable_return_Sum*100.0)/100.0;

            regis_MirasconToRegisTGSL_playable_return(id);
            refundNet(id);
            cancellationsRefundGross(id);

            cancellations_RegisTGSLtoMirascon_payableNet_Add = cancellations_RegisTGSLtoMirascon_payableNet(id);
            cancellations_RegisTGSLtoMirascon_payableNet_Sum += cancellations_RegisTGSLtoMirascon_payableNet_Add;
            cancellations_RegisTGSLtoMirascon_payableNet_Sum = Math.round(cancellations_RegisTGSLtoMirascon_payableNet_Sum*100.0)/100.0;

            cancellations_RegisTGSLtoMirascon_payableGross_Add = cancellations_RegisTGSLtoMirascon_payableGross(id);
            cancellations_RegisTGSLtoMirascon_payableGross_Sum += cancellations_RegisTGSLtoMirascon_payableGross_Add;
            cancellations_RegisTGSLtoMirascon_payableGross_Sum = Math.round(cancellations_RegisTGSLtoMirascon_payableGross_Sum*100.0)/100.0;

            pre_financingTool_GeicoFinancialToMirasconUpfront_payment_payable_Add = pre_financingTool_GeicoFinancialToMirasconUpfront_payment_payable(id);
            pre_financingTool_GeicoFinancialToMirasconUpfront_payment_payable_Sum += pre_financingTool_GeicoFinancialToMirasconUpfront_payment_payable_Add;
            pre_financingTool_GeicoFinancialToMirasconUpfront_payment_payable_Sum = Math.round(pre_financingTool_GeicoFinancialToMirasconUpfront_payment_payable_Sum*100.0)/100.0;

            pre_financingTool_GeicoFinancialToMirasconUpfront_paymentTotalReceived_Add = pre_financingTool_GeicoFinancialToMirasconUpfront_paymentTotalReceived(id);
            pre_financingTool_GeicoFinancialToMirasconUpfront_paymentTotalReceived_Sum += pre_financingTool_GeicoFinancialToMirasconUpfront_paymentTotalReceived_Add;
            pre_financingTool_GeicoFinancialToMirasconUpfront_paymentTotalReceived_Sum = Math.round(pre_financingTool_GeicoFinancialToMirasconUpfront_paymentTotalReceived_Sum*100.0)/100.0;

            pre_financingTool_MirasconToGeico_financialPaid_Add = pre_financingTool_MirasconToGeico_financialPaid(id);
            pre_financingTool_MirasconToGeico_financialPaid_Sum += pre_financingTool_MirasconToGeico_financialPaid_Add;
            pre_financingTool_MirasconToGeico_financialPaid_Sum = Math.round(pre_financingTool_MirasconToGeico_financialPaid_Sum*100.0)/100.0;

            pre_financingTool_MirasconToGeicoFinancialRefund_MTA_Add = pre_financingTool_MirasconToGeicoFinancialRefund_MTA(id);
            pre_financingTool_MirasconToGeicoFinancialRefund_MTA_Sum += pre_financingTool_MirasconToGeicoFinancialRefund_MTA_Add;
            pre_financingTool_MirasconToGeicoFinancialRefund_MTA_Sum = Math.round(pre_financingTool_MirasconToGeicoFinancialRefund_MTA_Sum*100.0)/100.0;

            pre_financingTool_MirasconToGeicoFinancial_payable_Add = pre_financingTool_MirasconToGeicoFinancial_payable(id);
            pre_financingTool_MirasconToGeicoFinancial_payable_Sum += pre_financingTool_MirasconToGeicoFinancial_payable_Add;
            pre_financingTool_MirasconToGeicoFinancial_payable_Sum = Math.round(pre_financingTool_MirasconToGeicoFinancial_payable_Sum*100.0)/100.0;

            pre_financingTool_MirasconToGeicoFinancialResidualDept_Add = pre_financingTool_MirasconToGeicoFinancialResidualDept(id);
            pre_financingTool_MirasconToGeicoFinancialResidualDept_Sum += pre_financingTool_MirasconToGeicoFinancialResidualDept_Add;
            pre_financingTool_MirasconToGeicoFinancialResidualDept_Sum = Math.round(pre_financingTool_MirasconToGeicoFinancialResidualDept_Sum*100.0)/100.0;
        }
        System.out.println("+_+_+_+Test SUM Table = +_+_+_+_+"+totalYearlyPremiumNet_Sum);
        setTableSum();
    }

    public void setTableSum() throws SQLException {
        jdbcDao.insertSumColumn(totalYearlyPremiumNet_Sum,
                premiumInclTaxPerInstallment_Sum,
                totalCommissionPerYear17_5_Sum,
                mirasconCommissionPerYear6_5_Sum,
                geicoGmbhCommissionPerYear11_Sum,
                ersProductionNetSettlementPeriod_Sum,
                erstProductiveInclTaxSettlementPeriod_Sum,
                ersProductionNetTotal_Sum,
                ersProductionInclTaxTotal_Sum,
                premiumInclTaxPaidYTD_Sum ,
                netPremiumPaidYTD_Sum ,
                total_17_5_payable_Sum ,
                premiumInclTaxPaid_Sum ,
                regisMirasconPayableNet_Sum,
                total_17_5_return_Sum ,
                total_17_5_playable_return_Sum,
                mirascon_6_5_payable_Sum ,
                mirasconToGeicoGmbh_11_payable_Sum,
                disbursementGeicoCommission_GeicoUkAgency_8_5_Sum,
                disbursementGeicoCommision_GeicoGmbh_2_5_Sum,
                totalGeicoCommisionYTD_Sum,
                regis_MirasconToRegisTGSL_playable_return_Sum,
                cancellations_RegisTGSLtoMirascon_payableNet_Sum,
                cancellations_RegisTGSLtoMirascon_payableGross_Sum,
                pre_financingTool_GeicoFinancialToMirasconUpfront_payment_payable_Sum,
                pre_financingTool_GeicoFinancialToMirasconUpfront_paymentTotalReceived_Sum,
                pre_financingTool_MirasconToGeicoFinancial_payable_Sum,
                pre_financingTool_MirasconToGeico_financialPaid_Sum,
                pre_financingTool_MirasconToGeicoFinancialRefund_MTA_Sum,
                pre_financingTool_MirasconToGeicoFinancialResidualDept_Sum);
    }

    public Date getSettlementStartDate(){
        return settlementPeriod_startDate;
    }

    public void setSettlementPeriod_startDate(java.sql.Date settlementPeriod_startDate){
        this.settlementPeriod_startDate = settlementPeriod_startDate;
    }

    public Date getSettlementEndDate(){
        return settlementPeriod_endDate;
    }

    public void setSettlementPeriod_endDate(java.sql.Date settlementPeriod_endDate){
        this.settlementPeriod_endDate = settlementPeriod_endDate;
    }

    public void setMathRoundInColumn(int id) throws SQLException {
        totalYearlyPremiumIncl_Tax = jdbcDao.selectColumnDoubleValue_WithID(id, 11);
        totalYearlyPremiumIncl_Tax = Math.round(totalYearlyPremiumIncl_Tax*100.0)/100.0;
        jdbcDao.updateColumnDoubleValue_WithIdAndValue(id, 11, totalYearlyPremiumIncl_Tax);
    }

    public Date smallestDateAccounting(int id) throws SQLException{
        dateFromColumns.clear();
        getTheSmallest(id);
        return date;
    }

    private void getTheSmallest(int id) throws SQLException{
        System.out.println("Start Sort Algorithmus");
        Date dateValue;
        for (int insertData = 0; insertData <= 16; insertData++) {
            dateValue = jdbcDao.selectAccountingColumnDateValue_WithID(id, (insertData+26));
            if(dateValue != null) {
                dateFromColumns.add(dateValue);
            }
        }
        if(dateFromColumns.size()== 0){
            date = date_0;
        }else{
            Collections.sort(dateFromColumns);
            System.out.println("List Size is : "+dateFromColumns.size());
            date = dateFromColumns.get(0);
            System.out.println("........This is the smallest date....... "+dateFromColumns.get(0));
        }
    }

    public void setArrayWithDates(int id) throws SQLException, ParseException {
        System.out.println("I select the data in the array now!");
        //Range is 26 until 42
        for (int insertData = 0; insertData < 16; insertData++) {
            if(jdbcDao.selectAccountingColumnDateValue_WithID(id, (insertData+26)) != null){
                dateFromColumns.add(jdbcDao.selectAccountingColumnDateValue_WithID(id, (insertData+26)));
            }
            /*if(dateFromColumns[insertData] == null){
                dateFromColumns[insertData] = new java.sql.Date(df.parse("0000-00-00").getTime());
                System.out.println("I insert the data from index "+insertData+" with the 0000-00-00");dateFromColumns[insertData] = jdbcDao.selectAccountingColumnDateValue_WithID(1, (insertData+26));
            }*/
            //System.out.println("I insert the data from index "+insertData+" with the value " +dateFromColumns[insertData]);
        }Collections.sort(dateFromColumns);
            /*java.sql.Date exchangeValue;for (int compareNextValue = 0; compareNextValue < dateFromColumns.length; compareNextValue++) {
                for (int columnIndex = dateFromColumns.length - 1; columnIndex > 0; columnIndex--) {
                        System.out.println("Check if it is null at index " + columnIndex + " : " +dateFromColumns[columnIndex]);
                        if (dateFromColumns[columnIndex - 1].compareTo(dateFromColumns[columnIndex]) > 0) {
                            exchangeValue = dateFromColumns[columnIndex];
                            dateFromColumns[columnIndex] = dateFromColumns[columnIndex - 1];
                            dateFromColumns[columnIndex - 1] = exchangeValue;
                            System.out.println("Exchange");
                        }
                    }
                }*/
    }


    public void expireDate(int id) throws SQLException, ParseException {
        System.out.println("ExpireDate");
        if (effectiveDate != null) {
            effectiveDateYear = effectiveDateYear + 1;
            effectiveDateDay = effectiveDateDay - 1;

            expireDate = new java.sql.Date(df.parse(effectiveDateYear + "-" + effectiveDateMonth + "-" + effectiveDateDay).getTime());

            System.out.println("Update ExpireDate = " + expireDate);
            jdbcDao.updateColumnDateValue_WithIdAndValue(id, 9, expireDate);

            expireDate = jdbcDao.selectColumnDateValue_WithID(id, 9);
            System.out.println("Select ExpireDate = " + expireDate);
        }
    }

    public double totalYearlyPremiumNet(int id) throws SQLException {
        System.out.println("totalYearlyPremiumNet");
        totalYearlyPremiumIncl_Tax = jdbcDao.selectColumnDoubleValue_WithID(id, 11);

        if (effectiveDate.compareTo(date_2016_10_1) < 0) {
            totalYearlyPremiumNet = totalYearlyPremiumIncl_Tax / v1_095;
            totalYearlyPremiumNet = Math.round(totalYearlyPremiumNet*100.0)/100.0;
            jdbcDao.updateColumnDoubleValue_WithIdAndValue(id, 10, totalYearlyPremiumNet);
        } else {
            if (effectiveDate.compareTo(date_2016_10_1) <= 0 && effectiveDate.compareTo(date_2017_6_1) < 0) {
                totalYearlyPremiumNet = totalYearlyPremiumIncl_Tax / v1_1;
                totalYearlyPremiumNet = Math.round(totalYearlyPremiumNet*100.0)/100.0;
                jdbcDao.updateColumnDoubleValue_WithIdAndValue(id, 10, totalYearlyPremiumNet);
            } else {
                totalYearlyPremiumNet = totalYearlyPremiumIncl_Tax / v1_12;
                totalYearlyPremiumNet = Math.round(totalYearlyPremiumNet*100.0)/100.0;
                jdbcDao.updateColumnDoubleValue_WithIdAndValue(id, 10, totalYearlyPremiumNet);
            }
        }
        return totalYearlyPremiumNet;
    }

    public void dailyPremiumNet(int id) throws SQLException {
        System.out.println("dailyPremiumNet");
        dailyPremiumNet = totalYearlyPremiumNet / daysPerYear;
        dailyPremiumNet = Math.round(dailyPremiumNet*100.0)/100.0;
        jdbcDao.updateColumnDoubleValue_WithIdAndValue(id, 12, dailyPremiumNet);
    }

    public void dailyPremiumInclTax(int id) throws SQLException {
        System.out.println("dailyPremiumInclTax");
        dailyPremiumInclTax = totalYearlyPremiumIncl_Tax / daysPerYear;
        dailyPremiumInclTax = Math.round(dailyPremiumInclTax*100.0)/100.0;
        jdbcDao.updateColumnDoubleValue_WithIdAndValue(id, 13, dailyPremiumInclTax);
    }

    public double totalCommissionPerYear17_5(int id) throws SQLException {
        System.out.println("totalCommisionPerYear17_5");
        totalCommissionPerYear17_5 = ( totalYearlyPremiumNet * p17_5)/100;
        totalCommissionPerYear17_5 = Math.round(totalCommissionPerYear17_5*100.0)/100.0;
        jdbcDao.updateColumnDoubleValue_WithIdAndValue(id, 14, totalCommissionPerYear17_5);
        return totalCommissionPerYear17_5;
    }

    public double mirasconCommissionPerYear6_5(int id) throws SQLException {
        System.out.println("mirasconCommissionPerYear_6_5");
        mirasconCommissionPerYear6_5 = (p6_5 * totalYearlyPremiumNet)/100;
        mirasconCommissionPerYear6_5 = Math.round(mirasconCommissionPerYear6_5*100.0)/100.0;
        jdbcDao.updateColumnDoubleValue_WithIdAndValue(id, 15, mirasconCommissionPerYear6_5);
        return mirasconCommissionPerYear6_5;
    }

    public double geicoGmbhCommissionPerYear11(int id) throws SQLException {
        System.out.println("geicoGmbhCommisionPerYear11");
        geicoGmbhCommissionPerYear11 = (11 * totalYearlyPremiumNet)/100;
        geicoGmbhCommissionPerYear11 = Math.round(geicoGmbhCommissionPerYear11*100.0)/100.0;
        jdbcDao.updateColumnDoubleValue_WithIdAndValue(id, 16, geicoGmbhCommissionPerYear11);
        return geicoGmbhCommissionPerYear11;
    }

    public void ofInstallmentsPerYear(int id) throws SQLException {
        System.out.println("ofInstallmentsPerYear. Correct");
        modeOfPayments = jdbcDao.selectColumnStringValue_WithID(1, 17);
        if (modeOfPayments == "montly") {
            ofInstallmentsPerYear = 12;
            jdbcDao.updateColumnIntValue_WithIdAndValue(id, 18, ofInstallmentsPerYear);
        } else {
            ofInstallmentsPerYear = 1;
            jdbcDao.updateColumnIntValue_WithIdAndValue(id, 18, ofInstallmentsPerYear);
        }
    }

    public double premiumInclTaxPerInstallment(int id) throws SQLException {
        System.out.println("premiumInclTaxPerInstallment = 477.");
        premiumInclTaxPerInstallment = totalYearlyPremiumIncl_Tax * ofInstallmentsPerYear;
        premiumInclTaxPerInstallment = Math.round(premiumInclTaxPerInstallment*100.0)/100.0;
        jdbcDao.updateColumnDoubleValue_WithIdAndValue(id, 19, premiumInclTaxPerInstallment);
        return premiumInclTaxPerInstallment;
    }

    public void netPremiumPerInstallment(int id) throws SQLException {
        System.out.println("netPremiumPerInstallment");
        if (effectiveDate.compareTo(date_2016_10_1) < 0) {
            netPremiumPerInstallment = premiumInclTaxPerInstallment / v1_095;
            netPremiumPerInstallment = Math.round(netPremiumPerInstallment*100.0)/100.0;
            jdbcDao.updateColumnDoubleValue_WithIdAndValue(id, 20, netPremiumPerInstallment);
        } else {
            if (effectiveDate.compareTo(date_2016_10_1) >= 0 && effectiveDate.compareTo(date_2017_6_1) < 0) {
                netPremiumPerInstallment = premiumInclTaxPerInstallment / v1_1;
                netPremiumPerInstallment = Math.round(netPremiumPerInstallment*100.0)/100.0;
                jdbcDao.updateColumnDoubleValue_WithIdAndValue(id, 20, netPremiumPerInstallment);
            } else {
                netPremiumPerInstallment = premiumInclTaxPerInstallment / v1_12;
                netPremiumPerInstallment = Math.round(netPremiumPerInstallment*100.0)/100.0;
                jdbcDao.updateColumnDoubleValue_WithIdAndValue(id, 20, netPremiumPerInstallment);
            }
        }
    }
//not right
    /*=IF(AND(MIN(Accounting!Z201:CJZ201)>=$D$3;MIN(Accounting!Z201:CJZ201)<=$E$3;AN201=0);
    J201;
    IF(AND(MIN(Accounting!Z201:CJZ201)<$D$3;AN201=0);
    0;
    IF(AND(MIN(Accounting!Z201:CJZ201)>=$D$3;MIN(Accounting!Z201:CJZ201)<=$E$3;AN201>=$D$3;AN201<=$E$3);
    J201-AO201;
    IF(AND(MIN(Accounting!Z201:CJZ201)<$D$3;AN201>=$D$3;AN201<=$E$3);
    -AO201;
    0))))*/
    public double ersProductionNetSettlementPeriod(int id) throws SQLException, ParseException {
        System.out.println("ErsProductionNetSettlementPeriod with ID : " + id);
        refundDate = jdbcDao.selectColumnDateValue_WithID(id, 39);
        refundNet = jdbcDao.selectColumnDoubleValue_WithID(id, 40);
        //startDate: 2018-10-01; endDate: 2018-10-30 39
        //refundDate = date_0;
        refundDate = jdbcDao.selectColumnDateValue_WithID(id,39);
        System.out.println("ERS -- Get All Values 1.Start: "+settlementPeriod_startDate+" 2.End: "+settlementPeriod_endDate+" 3.Refund: "+refundDate+" 4.Smallest: "+smallestDateAccounting(id)+" Date 0: "+date_0);
        if (smallestDateAccounting(id).compareTo(settlementPeriod_startDate) >= 0 && smallestDateAccounting(id).compareTo(settlementPeriod_endDate) <= 0){
            if (refundDate == null) {
                System.out.println("ERS --- Here must be stay the 188 ID!");
                totalYearlyPremiumNet = jdbcDao.selectColumnDoubleValue_WithID(id, 10);
                ersProductionNetSettlementPeriod = totalYearlyPremiumNet;
                //ersProductionNetSettlementPeriod = Math.round(ersProductionNetSettlementPeriod*100.0)/100.0;
                jdbcDao.updateColumnDoubleValue_WithIdAndValue(id, 21, ersProductionNetSettlementPeriod);
            }
        } else {
            if ((smallestDateAccounting(id).compareTo(settlementPeriod_startDate) < 0) && (refundDate == null)) {
                ersProductionNetSettlementPeriod = v0_0;
                System.out.println("ERS --- 1. Hi here is a mistake!");
                jdbcDao.updateColumnDoubleValue_WithIdAndValue(id, 21, ersProductionNetSettlementPeriod);
            } else {
                if ((smallestDateAccounting(id).compareTo(settlementPeriod_startDate) >= 0) && (smallestDateAccounting(id).compareTo(settlementPeriod_endDate) <= 0) && (refundDate.compareTo(settlementPeriod_endDate) >= 0) && (refundDate.compareTo(settlementPeriod_startDate) <= 0)) {
                    ersProductionNetSettlementPeriod = totalYearlyPremiumIncl_Tax - refundNet;
                    //ersProductionNetSettlementPeriod = Math.round(ersProductionNetSettlementPeriod*100.0)/100.0;
                    jdbcDao.updateColumnDoubleValue_WithIdAndValue(id, 21, ersProductionNetSettlementPeriod);
                } else {
                    if ((smallestDateAccounting(id).compareTo(settlementPeriod_startDate) < 0) && (refundDate.compareTo(settlementPeriod_startDate) >= 0) && (refundDate.compareTo(settlementPeriod_endDate) <= 0)) {
                        ersProductionNetSettlementPeriod = refundNet;
                        //ersProductionNetSettlementPeriod = Math.round(ersProductionNetSettlementPeriod*100.0)/100.0;
                        jdbcDao.updateColumnDoubleValue_WithIdAndValue(id, 21, ersProductionNetSettlementPeriod);
                    } else {
                        ersProductionNetSettlementPeriod = v0_0;
                        System.out.println("ERS --- 2. Hi here is a mistake!");
                        //ersProductionNetSettlementPeriod = Math.round(ersProductionNetSettlementPeriod*100.0)/100.0;
                        jdbcDao.updateColumnDoubleValue_WithIdAndValue(id, 21, ersProductionNetSettlementPeriod);
                    }
                }
            }
        }
        return ersProductionNetSettlementPeriod;
    }

    public double erstProductiveInclTaxSettlementPeriod(int id) throws SQLException {
        System.out.println("ersProductiveInclSettlementPeriod");
        if (effectiveDate.compareTo(date_2016_10_1) < 0) {
            erstProductiveInclTaxSettlementPeriod = ersProductionNetSettlementPeriod * v1_095;
            jdbcDao.updateColumnDoubleValue_WithIdAndValue(id, 22, erstProductiveInclTaxSettlementPeriod);
        } else {
            if (effectiveDate.compareTo(date_2016_10_1) >= 0 && effectiveDate.compareTo(date_2017_6_1) < 0) {
                erstProductiveInclTaxSettlementPeriod = ersProductionNetSettlementPeriod * v1_1;
                jdbcDao.updateColumnDoubleValue_WithIdAndValue(id, 22, erstProductiveInclTaxSettlementPeriod);
            } else {
                erstProductiveInclTaxSettlementPeriod = ersProductionNetSettlementPeriod * v1_12;
                jdbcDao.updateColumnDoubleValue_WithIdAndValue(id, 22, erstProductiveInclTaxSettlementPeriod);
            }
        }
        return erstProductiveInclTaxSettlementPeriod;
    }
//not right
    public double ersProductionNetTotal(int id) throws SQLException, ParseException {
        System.out.println("ersProductionNetTotal");
        refundDate = jdbcDao.selectColumnDateValue_WithID(id,39);
        //accountingPaymentTotal = jdbcDao.selectAccountingColumnDoubleValue_WithID(1, 12);
        accountingPaymentTotal = jdbcDao.selectAccountingColumnIntValue_WithID(id,12);
        accountingPaymentTotal = 1;
        if (accountingPaymentTotal == 0) {
            ersProductionNetTotal = v0_0;
            ersProductionNetTotal = Math.round(ersProductionNetTotal *100.0)/100.0;
            jdbcDao.updateColumnDoubleValue_WithIdAndValue(id, 23, ersProductionNetTotal);
        } else {
            if (smallestDateAccounting(id).compareTo(settlementPeriod_endDate) <= 0 && refundDate == null) {
                ersProductionNetTotal = totalYearlyPremiumNet;
                ersProductionNetTotal = Math.round(ersProductionNetTotal *100.0)/100.0;
                jdbcDao.updateColumnDoubleValue_WithIdAndValue(id, 23, ersProductionNetTotal);
            } else {
                //check this
                if (smallestDateAccounting(id).compareTo(settlementPeriod_endDate) <= 0 && refundDate != null && refundDate.compareTo(settlementPeriod_endDate) <= 0) {
                    ersProductionNetTotal = totalYearlyPremiumNet - refundNet;
                    ersProductionNetTotal = Math.round(ersProductionNetTotal *100.0)/100.0;
                    jdbcDao.updateColumnDoubleValue_WithIdAndValue(id, 23, ersProductionNetTotal);
                } else {
                    ersProductionNetTotal = totalYearlyPremiumNet;
                    ersProductionNetTotal = Math.round(ersProductionNetTotal *100.0)/100.0;
                    jdbcDao.updateColumnDoubleValue_WithIdAndValue(id, 23, ersProductionNetTotal);
                }
            }
        }
        return ersProductionNetTotal;
    }
//not right
    public double ersProductionInclTaxTotal(int id) throws SQLException {
        System.out.println("ersProductionInclTotal");
        if (effectiveDate.compareTo(date_2016_10_1) < 0) {
            ersProductionInclTaxTotal = ersProductionNetTotal * v1_095;
            ersProductionInclTaxTotal = Math.round(ersProductionInclTaxTotal *100.0)/100.0;
            jdbcDao.updateColumnDoubleValue_WithIdAndValue(id, 24, ersProductionInclTaxTotal);
        } else {
            if (effectiveDate.compareTo(date_2016_10_1) >= 0 && effectiveDate.compareTo(date_2017_6_1) < 0) {
                ersProductionInclTaxTotal = ersProductionNetTotal * v1_1;
                ersProductionInclTaxTotal = Math.round(ersProductionInclTaxTotal *100.0)/100.0;
                jdbcDao.updateColumnDoubleValue_WithIdAndValue(id, 24, ersProductionInclTaxTotal);
            } else {
                ersProductionInclTaxTotal = ersProductionNetTotal * v1_12;
                ersProductionInclTaxTotal = Math.round(ersProductionInclTaxTotal *100.0)/100.0;
                jdbcDao.updateColumnDoubleValue_WithIdAndValue(id, 24, ersProductionInclTaxTotal);
            }
        }
        return ersProductionInclTaxTotal;
    }

    public void ofPaidInstallmentsYTD(int id) throws SQLException {
        if (accountingPaymentTotal == 0) {
            ofPaidInstallmentsYTD = 0;
            jdbcDao.updateColumnDoubleValue_WithIdAndValue(id, 25, ofPaidInstallmentsYTD);
        } else {
            ofPaidInstallmentsYTD = accountingPaymentTotal;
            jdbcDao.updateColumnDoubleValue_WithIdAndValue(id, 25, ofPaidInstallmentsYTD);
        }
    }

    public void ofUnpaidInstallmentsYTD(int id) throws SQLException {
        ofUnpaidInstallmentsYTD = ofInstallmentsPerYear - ofPaidInstallmentsYTD;
        jdbcDao.updateColumnDoubleValue_WithIdAndValue(id, 26, ofUnpaidInstallmentsYTD);
    }

    public double premiumInclTaxPaid(int id) throws SQLException {
        premiumInclTaxPaid = accountingPaymentPeriod * premiumInclTaxPerInstallment;
        premiumInclTaxPaid = Math.round(premiumInclTaxPaid*100.0)/100.0;
        jdbcDao.updateColumnDoubleValue_WithIdAndValue(id, 27, premiumInclTaxPaid);
        return premiumInclTaxPaid;
    }

    public double premiumInclTaxPaidYTD(int id) throws SQLException {
        premiumInclTaxPaidYTD = ofPaidInstallmentsYTD * premiumInclTaxPerInstallment;
        premiumInclTaxPaidYTD = Math.round(premiumInclTaxPaidYTD*100.0)/100.0;
        jdbcDao.updateColumnDoubleValue_WithIdAndValue(id, 28, premiumInclTaxPaidYTD);
        return premiumInclTaxPaidYTD;
    }

    public double netPremiumPaidYTD(int id) throws SQLException {
        if (effectiveDate.compareTo(date_2016_10_1) < 0) {
            netPremiumPaidYTD = premiumInclTaxPaidYTD / v1_095;
            netPremiumPaidYTD = Math.round(netPremiumPaidYTD*100.0)/100.0;
            jdbcDao.updateColumnDoubleValue_WithIdAndValue(id, 29, netPremiumPaidYTD);
        } else {
            if (effectiveDate.compareTo(date_2016_10_1) >= 0 && effectiveDate.compareTo(date_2017_6_1) < 0) {
                netPremiumPaidYTD = premiumInclTaxPaidYTD / v1_1;
                netPremiumPaidYTD = Math.round(netPremiumPaidYTD*100.0)/100.0;
                jdbcDao.updateColumnDoubleValue_WithIdAndValue(id, 29, netPremiumPaidYTD);
            } else {
                netPremiumPaidYTD = premiumInclTaxPaidYTD / v1_12;
                netPremiumPaidYTD = Math.round(netPremiumPaidYTD*100.0)/100.0;
                jdbcDao.updateColumnDoubleValue_WithIdAndValue(id, 29, netPremiumPaidYTD);
            }
        }return netPremiumPaidYTD;
    }

    public double total_17_5_payable(int id) throws SQLException, ParseException {
        if (smallestDateAccounting(id).compareTo(settlementPeriod_startDate) >= 0 && smallestDateAccounting(id).compareTo(settlementPeriod_endDate) <= 0) {
            total_17_5_payable = totalYearlyPremiumNet * p17_5;
            total_17_5_payable = Math.round(total_17_5_payable*100.0)/100.0;
            jdbcDao.updateColumnDoubleValue_WithIdAndValue(id, 30, total_17_5_payable);
        } else {
            total_17_5_payable = v0_0;
            total_17_5_payable = Math.round(total_17_5_payable*100.0)/100.0;
            jdbcDao.updateColumnDoubleValue_WithIdAndValue(id, 30, total_17_5_payable);
        }
        return total_17_5_payable;
    }
//here is null refund Date
    public double total_17_5_return(int id) throws SQLException, ParseException {
        regisMirasconPayableNet = jdbcDao.selectColumnDoubleValue_WithID(1, 42);
        refundDate = jdbcDao.selectColumnDateValue_WithID(id,39);
        if (refundDate != null) {
            if (refundDate.compareTo(settlementPeriod_startDate) >= 0 && refundDate.compareTo(settlementPeriod_endDate) <= 0) {
                total_17_5_return = regisMirasconPayableNet * p17_5;
                total_17_5_return = Math.round(total_17_5_return*100.0)/100.0;
                jdbcDao.updateColumnDoubleValue_WithIdAndValue(id, 31, total_17_5_return);
            }else {
                total_17_5_return = v0_0;
                jdbcDao.updateColumnDoubleValue_WithIdAndValue(id, 31, total_17_5_return);
            }
        } else {
            total_17_5_return = v0_0;
            jdbcDao.updateColumnDoubleValue_WithIdAndValue(id, 31, total_17_5_return);
        }
        return total_17_5_return;
    }

    public double total_17_5_playable_return(int id) throws SQLException {
        Double roundIt = total_17_5_payable + total_17_5_return;
        total_17_5_playable_return = Double.valueOf(Math.round(roundIt * 100) / 100);
        total_17_5_playable_return = Math.round(total_17_5_playable_return*100.0)/100.0;
        jdbcDao.updateColumnDoubleValue_WithIdAndValue(id, 32, total_17_5_playable_return);
        return total_17_5_playable_return;
    }

    public double mirascon_6_5_payable(int id) throws SQLException {
        mirascon_6_5_payable = (total_17_5_playable_return / p17_5) * p6_5;
        mirascon_6_5_payable = Math.round(mirascon_6_5_payable*100.0)/100.0;
        jdbcDao.updateColumnDoubleValue_WithIdAndValue(id, 33, mirascon_6_5_payable);
        return mirascon_6_5_payable;
    }

    public double mirasconToGeicoGmbh_11_payable(int id) throws SQLException {
        mirasconToGeicoGmbh_11_payable = (total_17_5_playable_return / p17_5) * p6_5;
        mirasconToGeicoGmbh_11_payable = Math.round(mirasconToGeicoGmbh_11_payable*100.0)/100.0;
        jdbcDao.updateColumnDoubleValue_WithIdAndValue(id, 34, mirasconToGeicoGmbh_11_payable);
        return mirasconToGeicoGmbh_11_payable;
    }

    public double disbursementGeicoCommission_GeicoUkAgency_8_5(int id) throws SQLException {
        disbursementGeicoCommission_GeicoUkAgency_8_5 = (total_17_5_playable_return / p17_5) * p6_5;
        disbursementGeicoCommission_GeicoUkAgency_8_5 = Math.round(disbursementGeicoCommission_GeicoUkAgency_8_5*100.0)/100.0;
        jdbcDao.updateColumnDoubleValue_WithIdAndValue(id, 35, disbursementGeicoCommission_GeicoUkAgency_8_5);
        return disbursementGeicoCommission_GeicoUkAgency_8_5;
    }

    public double disbursementGeicoCommision_GeicoGmbh_2_5(int id) throws SQLException {
        disbursementGeicoCommision_GeicoGmbh_2_5 = (total_17_5_playable_return / p17_5) * p2_5;
        disbursementGeicoCommision_GeicoGmbh_2_5 = Math.round(disbursementGeicoCommision_GeicoGmbh_2_5*100.0)/100.0;
        jdbcDao.updateColumnDoubleValue_WithIdAndValue(id, 36, disbursementGeicoCommision_GeicoGmbh_2_5);
        return disbursementGeicoCommision_GeicoGmbh_2_5;
    }

    public double totalGeicoCommisionYTD(int id) throws SQLException {
        totalGeicoCommisionYTD = (ersProductionNetTotal * p11_0)/100;
        totalGeicoCommisionYTD = Math.round(totalGeicoCommisionYTD*100.0)/100.0;
        jdbcDao.updateColumnDoubleValue_WithIdAndValue(id, 37, totalGeicoCommisionYTD);
        return totalGeicoCommisionYTD;
    }
//wrong
    public double regis_MirasconToRegisTGSL_playable_return(int id) throws SQLException, ParseException {
        double cache1;
        double cache2 = 0.0;
        System.out.println("+++++regis_MirasconToRegisTGSL_playable_return++++++");
        refundDate = jdbcDao.selectColumnDateValue_WithID(id,39);
        if (total_17_5_playable_return == v0_0) {
            regis_MirasconToRegisTGSL_playable_return = 0.0;
            jdbcDao.updateColumnDoubleValue_WithIdAndValue(id, 38, regis_MirasconToRegisTGSL_playable_return);
        } else {
            if (total_17_5_playable_return != v0_0) { //AF14

                if (total_17_5_payable != v0_0) { // AD14
                    System.out.println("1. Hi here is a mistake!");
                    cache1 = totalYearlyPremiumIncl_Tax; //K14

                } else {
                    System.out.println("v0.0");
                    cache1 = v0_0;
                }
                if (refundDate != null) {
                    if (refundDate.compareTo(settlementPeriod_startDate) >= 0 && refundDate.compareTo(settlementPeriod_endDate) <= 0) {
                        System.out.println("2. Hi here is a mistake!");
                        cache2 = cancellations_RegisTGSLtoMirascon_payableGross;
                    }
                }
                else {
                    System.out.println("v0.0");
                    cache2 = v0_0;
                }
                System.out.println("I'm outside! :P ");
                regis_MirasconToRegisTGSL_playable_return = cache1 - cache2- total_17_5_playable_return;
                regis_MirasconToRegisTGSL_playable_return = Math.round(regis_MirasconToRegisTGSL_playable_return*100.0)/100.0;
                jdbcDao.updateColumnDoubleValue_WithIdAndValue(id, 38, regis_MirasconToRegisTGSL_playable_return);
            } else {
                regis_MirasconToRegisTGSL_playable_return = 0.0;
                jdbcDao.updateColumnDoubleValue_WithIdAndValue(id, 38, regis_MirasconToRegisTGSL_playable_return);
            }
        }
        return regis_MirasconToRegisTGSL_playable_return;
    }

    public void refundNet(int id) throws SQLException, ParseException {
        cancellationDate = jdbcDao.selectColumnDateValue_WithID(id, 8);
        if (ofPaidInstallmentsYTD == v0_0) {
            refundNet = v0_0;
            refundNet = Math.round(refundNet*100.0)/100.0;
            jdbcDao.updateColumnDoubleValue_WithIdAndValue(id, 40, refundNet);
        } else {
            if (cancellationDate.compareTo(date_0) != 0) {
                if (effectiveDate.compareTo(cancellationDate) == 0) {
                    refundNet = totalYearlyPremiumNet;
                    refundNet = Math.round(refundNet*100.0)/100.0;
                    jdbcDao.updateColumnDoubleValue_WithIdAndValue(id, 40, refundNet);
                } else {
                    //please check this instruction
                    if ((cancellationDate.compareTo(effectiveDate)) < 15) {
                        Double betwixt = dailyPremiumNet * (cancellationDate.compareTo(effectiveDate));
                        if (betwixt > 25) {
                            refundNet = totalYearlyPremiumNet - (dailyPremiumNet * (cancellationDate.compareTo(effectiveDate)));
                            refundNet = Math.round(refundNet*100.0)/100.0;
                            jdbcDao.updateColumnDoubleValue_WithIdAndValue(id, 40, refundNet);
                        } else {
                            refundNet = totalYearlyPremiumNet - 25;
                            refundNet = Math.round(refundNet*100.0)/100.0;
                            jdbcDao.updateColumnDoubleValue_WithIdAndValue(id, 40, refundNet);
                        }
                    } else {
                        if (cancellationDate.compareTo(effectiveDate) > 14) {
                            if ((totalYearlyPremiumNet - 25 - (cancellationDate.compareTo(effectiveDate)) * dailyPremiumNet) < 0) {
                                refundNet = v0_0;
                                refundNet = Math.round(refundNet*100.0)/100.0;
                                jdbcDao.updateColumnDoubleValue_WithIdAndValue(id, 40, refundNet);
                            } else {
                                refundNet = ((totalYearlyPremiumNet - 25) - cancellationDate.compareTo(effectiveDate)) * dailyPremiumNet;
                                refundNet = Math.round(refundNet*100.0)/100.0;
                                jdbcDao.updateColumnDoubleValue_WithIdAndValue(id, 40, refundNet);
                            }
                        } else {
                            refundNet = v0_0;
                            refundNet = Math.round(refundNet*100.0)/100.0;
                            jdbcDao.updateColumnDoubleValue_WithIdAndValue(id, 40, refundNet);
                        }
                    }
                }
            } else {
                refundNet = v0_0;
                refundNet = Math.round(refundNet*100.0)/100.0;
                jdbcDao.updateColumnDoubleValue_WithIdAndValue(id, 40, refundNet);
            }
        }
    }

    public double cancellationsRefundGross(int id) throws SQLException {
        if (effectiveDate.compareTo(date_2016_10_1) < 0) {
            cancellationsRefundGross = refundNet * v1_095;
             cancellationsRefundGross = Math.round(cancellationsRefundGross*100.0)/100.0;
            jdbcDao.updateColumnDoubleValue_WithIdAndValue(id, 41, cancellationsRefundGross);
        } else {
            if (effectiveDate.compareTo(date_2016_10_1) >= 0 && effectiveDate.compareTo(date_2017_6_1) < 0) {
                cancellationsRefundGross = refundNet * v1_1;
                cancellationsRefundGross = Math.round(cancellationsRefundGross*100.0)/100.0;
                jdbcDao.updateColumnDoubleValue_WithIdAndValue(id, 41, cancellationsRefundGross);
            } else {
                cancellationsRefundGross = refundNet * v1_12;
                cancellationsRefundGross = Math.round(cancellationsRefundGross*100.0)/100.0;
                jdbcDao.updateColumnDoubleValue_WithIdAndValue(id, 41, cancellationsRefundGross);
            }
        }
        return cancellationsRefundGross;
    }

    public double cancellations_RegisTGSLtoMirascon_payableNet(int id) throws SQLException, ParseException {
        refundDate = jdbcDao.selectColumnDateValue_WithID(id,39);
        if (ofPaidInstallmentsYTD == 0) {
            cancellations_RegisTGSLtoMirascon_payableNet = v0_0;
            cancellations_RegisTGSLtoMirascon_payableNet = Math.round(cancellations_RegisTGSLtoMirascon_payableNet*100.0)/100.0;
            jdbcDao.updateColumnDoubleValue_WithIdAndValue(id, 42, cancellations_RegisTGSLtoMirascon_payableNet);
        } else {
            if (refundDate != null) {
                if (refundDate.compareTo(settlementPeriod_startDate) >= 0 && refundDate.compareTo(settlementPeriod_endDate) >= 0) {
                    if (cancellationDate.compareTo(date_0) != 0 && refundDate != null) {
                        cancellations_RegisTGSLtoMirascon_payableNet = refundNet;
                        cancellations_RegisTGSLtoMirascon_payableNet = Math.round(cancellations_RegisTGSLtoMirascon_payableNet*100.0)/100.0;
                        jdbcDao.updateColumnDoubleValue_WithIdAndValue(id, 42, cancellations_RegisTGSLtoMirascon_payableNet);
                    } else {
                        cancellations_RegisTGSLtoMirascon_payableNet = v0_0;
                        cancellations_RegisTGSLtoMirascon_payableNet = Math.round(cancellations_RegisTGSLtoMirascon_payableNet*100.0)/100.0;
                        jdbcDao.updateColumnDoubleValue_WithIdAndValue(id, 42, cancellations_RegisTGSLtoMirascon_payableNet);
                    }
                }
                else {
                    cancellations_RegisTGSLtoMirascon_payableNet = v0_0;
                    cancellations_RegisTGSLtoMirascon_payableNet = Math.round(cancellations_RegisTGSLtoMirascon_payableNet*100.0)/100.0;
                    jdbcDao.updateColumnDoubleValue_WithIdAndValue(id, 42, cancellations_RegisTGSLtoMirascon_payableNet);
                }
            } else {
                cancellations_RegisTGSLtoMirascon_payableNet = v0_0;
                cancellations_RegisTGSLtoMirascon_payableNet = Math.round(cancellations_RegisTGSLtoMirascon_payableNet*100.0)/100.0;
                jdbcDao.updateColumnDoubleValue_WithIdAndValue(id, 42, cancellations_RegisTGSLtoMirascon_payableNet);
            }
        }
        return cancellations_RegisTGSLtoMirascon_payableNet;
    }

    public double cancellations_RegisTGSLtoMirascon_payableGross(int id) throws SQLException {
        if (effectiveDate.compareTo(date_2016_10_1) >= 0) {
            cancellations_RegisTGSLtoMirascon_payableGross = cancellations_RegisTGSLtoMirascon_payableNet * v1_095;
            cancellations_RegisTGSLtoMirascon_payableGross = Math.round(cancellations_RegisTGSLtoMirascon_payableGross*100.0)/100.0;
            jdbcDao.updateColumnDoubleValue_WithIdAndValue(id, 43, cancellations_RegisTGSLtoMirascon_payableGross);
        } else {
            if (effectiveDate.compareTo(date_2016_10_1) >= 0 && effectiveDate.compareTo(date_2017_6_1) < 0) {
                cancellations_RegisTGSLtoMirascon_payableGross = cancellations_RegisTGSLtoMirascon_payableNet * v1_1;
                cancellations_RegisTGSLtoMirascon_payableGross = Math.round(cancellations_RegisTGSLtoMirascon_payableGross*100.0)/100.0;
                jdbcDao.updateColumnDoubleValue_WithIdAndValue(id, 42, cancellations_RegisTGSLtoMirascon_payableGross);
            } else {
                cancellations_RegisTGSLtoMirascon_payableGross = cancellations_RegisTGSLtoMirascon_payableNet * v1_12;
                cancellations_RegisTGSLtoMirascon_payableGross = Math.round(cancellations_RegisTGSLtoMirascon_payableGross*100.0)/100.0;
                jdbcDao.updateColumnDoubleValue_WithIdAndValue(id, 42, cancellations_RegisTGSLtoMirascon_payableGross);
            }
        }
        return cancellations_RegisTGSLtoMirascon_payableGross;
    }

    public double pre_financingTool_GeicoFinancialToMirasconUpfront_payment_payable(int id) throws SQLException, ParseException {
        if (ofInstallmentsPerYear == 12 && smallestDateAccounting(id).compareTo(settlementPeriod_startDate) >= 0 && smallestDateAccounting(id).compareTo(settlementPeriod_endDate) >= 0) {
            pre_financingTool_GeicoFinancialToMirasconUpfront_payment_payable = ofUnpaidInstallmentsYTD * premiumInclTaxPerInstallment;
            pre_financingTool_GeicoFinancialToMirasconUpfront_payment_payable = Math.round(pre_financingTool_GeicoFinancialToMirasconUpfront_payment_payable*100.0)/100.0;
            jdbcDao.updateColumnDoubleValue_WithIdAndValue(id, 43, pre_financingTool_GeicoFinancialToMirasconUpfront_payment_payable);
        } else {
            pre_financingTool_GeicoFinancialToMirasconUpfront_payment_payable = v0_0;
            pre_financingTool_GeicoFinancialToMirasconUpfront_payment_payable = Math.round(pre_financingTool_GeicoFinancialToMirasconUpfront_payment_payable*100.0)/100.0;
            jdbcDao.updateColumnDoubleValue_WithIdAndValue(id, 43, pre_financingTool_GeicoFinancialToMirasconUpfront_payment_payable);
        }
        return pre_financingTool_GeicoFinancialToMirasconUpfront_payment_payable;
    }

    public double pre_financingTool_GeicoFinancialToMirasconUpfront_paymentTotalReceived(int id) throws SQLException {
        accountingPaymentBegin = jdbcDao.selectAccountingColumnIntValue_WithID(1, 14);
        if (pre_financingTool_GeicoFinancialToMirasconUpfront_payment_payable == 0) {
            if (ofInstallmentsPerYear == 12 && ofPaidInstallmentsYTD > 0) {
                pre_financingTool_GeicoFinancialToMirasconUpfront_paymentTotalReceived = totalYearlyPremiumIncl_Tax * (premiumInclTaxPerInstallment * accountingPaymentBegin);
                pre_financingTool_GeicoFinancialToMirasconUpfront_paymentTotalReceived = Math.round(pre_financingTool_GeicoFinancialToMirasconUpfront_paymentTotalReceived*100.0)/100.0;
                jdbcDao.updateColumnDoubleValue_WithIdAndValue(id, 44, pre_financingTool_GeicoFinancialToMirasconUpfront_paymentTotalReceived);
            } else {
                pre_financingTool_GeicoFinancialToMirasconUpfront_paymentTotalReceived = v0_0;
                pre_financingTool_GeicoFinancialToMirasconUpfront_paymentTotalReceived = Math.round(pre_financingTool_GeicoFinancialToMirasconUpfront_paymentTotalReceived*100.0)/100.0;
                jdbcDao.updateColumnDoubleValue_WithIdAndValue(id, 44, pre_financingTool_GeicoFinancialToMirasconUpfront_paymentTotalReceived);
            }
        } else {
            pre_financingTool_GeicoFinancialToMirasconUpfront_paymentTotalReceived = v0_0;
            pre_financingTool_GeicoFinancialToMirasconUpfront_paymentTotalReceived = Math.round(pre_financingTool_GeicoFinancialToMirasconUpfront_paymentTotalReceived*100.0)/100.0;
            jdbcDao.updateColumnDoubleValue_WithIdAndValue(id, 44, pre_financingTool_GeicoFinancialToMirasconUpfront_paymentTotalReceived);
        }
        return pre_financingTool_GeicoFinancialToMirasconUpfront_paymentTotalReceived;
    }
// here is a bug, It is fixed
    public double pre_financingTool_MirasconToGeicoFinancial_payable(int id) throws SQLException {
        if (pre_financingTool_GeicoFinancialToMirasconUpfront_payment_payable < 0 && cancellations_RegisTGSLtoMirascon_payableGross >= pre_financingTool_GeicoFinancialToMirasconUpfront_payment_payable) {
            pre_financingTool_MirasconToGeicoFinancial_payable = pre_financingTool_GeicoFinancialToMirasconUpfront_payment_payable;
            pre_financingTool_MirasconToGeicoFinancial_payable= Math.round(pre_financingTool_MirasconToGeicoFinancial_payable*100.0)/100.0;
            jdbcDao.updateColumnDoubleValue_WithIdAndValue(id, 45, pre_financingTool_MirasconToGeicoFinancial_payable);
        } else {
            if (pre_financingTool_GeicoFinancialToMirasconUpfront_payment_payable > 0 && cancellations_RegisTGSLtoMirascon_payableGross < pre_financingTool_GeicoFinancialToMirasconUpfront_payment_payable) {
                pre_financingTool_MirasconToGeicoFinancial_payable = cancellations_RegisTGSLtoMirascon_payableGross;
                pre_financingTool_MirasconToGeicoFinancial_payable= Math.round(pre_financingTool_MirasconToGeicoFinancial_payable*100.0)/100.0;
                jdbcDao.updateColumnDoubleValue_WithIdAndValue(id, 45, pre_financingTool_MirasconToGeico_financialPaid);
            } else {
                if (ofPaidInstallmentsYTD == accountingPaymentBegin && refundDate == null) {
                    pre_financingTool_MirasconToGeicoFinancial_payable = v0_0;
                    pre_financingTool_MirasconToGeicoFinancial_payable= Math.round(pre_financingTool_MirasconToGeicoFinancial_payable*100.0)/100.0;
                    jdbcDao.updateColumnDoubleValue_WithIdAndValue(id, 45, pre_financingTool_MirasconToGeico_financialPaid);
                } else {
                    if (ofPaidInstallmentsYTD > accountingPaymentBegin && refundDate == null) {
                        pre_financingTool_MirasconToGeicoFinancial_payable = premiumInclTaxPerInstallment * accountingPaymentPeriod;
                        pre_financingTool_MirasconToGeicoFinancial_payable= Math.round(pre_financingTool_MirasconToGeicoFinancial_payable*100.0)/100.0;
                        jdbcDao.updateColumnDoubleValue_WithIdAndValue(id, 46, pre_financingTool_MirasconToGeico_financialPaid);
                    } else {
                        if (refundDate != null && cancellations_RegisTGSLtoMirascon_payableGross > 0) {
                            if ((premiumInclTaxPerInstallment * accountingPaymentPeriod + cancellations_RegisTGSLtoMirascon_payableGross + pre_financingTool_MirasconToGeico_financialPaid) <= pre_financingTool_GeicoFinancialToMirasconUpfront_paymentTotalReceived) {
                                pre_financingTool_MirasconToGeicoFinancial_payable = premiumInclTaxPerInstallment * accountingPaymentPeriod + cancellations_RegisTGSLtoMirascon_payableGross;
                                pre_financingTool_MirasconToGeicoFinancial_payable= Math.round(pre_financingTool_MirasconToGeicoFinancial_payable*100.0)/100.0;
                                jdbcDao.updateColumnDoubleValue_WithIdAndValue(id, 46, pre_financingTool_MirasconToGeico_financialPaid);
                            } else {
                                if (premiumInclTaxPerInstallment * accountingPaymentPeriod + cancellations_RegisTGSLtoMirascon_payableGross + (pre_financingTool_MirasconToGeico_financialPaid) > pre_financingTool_GeicoFinancialToMirasconUpfront_paymentTotalReceived) {
                                    pre_financingTool_MirasconToGeicoFinancial_payable = pre_financingTool_GeicoFinancialToMirasconUpfront_paymentTotalReceived - pre_financingTool_MirasconToGeico_financialPaid;
                                    pre_financingTool_MirasconToGeicoFinancial_payable= Math.round(pre_financingTool_MirasconToGeicoFinancial_payable*100.0)/100.0;
                                    jdbcDao.updateColumnDoubleValue_WithIdAndValue(id, 46, pre_financingTool_MirasconToGeico_financialPaid);
                                } else {
                                    pre_financingTool_MirasconToGeicoFinancial_payable = v0_0;
                                    pre_financingTool_MirasconToGeicoFinancial_payable= Math.round(pre_financingTool_MirasconToGeicoFinancial_payable*100.0)/100.0;
                                    jdbcDao.updateColumnDoubleValue_WithIdAndValue(id, 46, pre_financingTool_MirasconToGeico_financialPaid);
                                }
                            }
                        } else {
                            System.out.println("Check If Null : 1. "+ premiumInclTaxPerInstallment+ " 2. "+ accountingPaymentPeriod + " 3. " + cancellations_RegisTGSLtoMirascon_payableGross + " 4. " + pre_financingTool_MirasconToGeico_financialPaid+ " 5. "+ pre_financingTool_GeicoFinancialToMirasconUpfront_paymentTotalReceived);
                            if ((premiumInclTaxPerInstallment * accountingPaymentPeriod + cancellations_RegisTGSLtoMirascon_payableGross + pre_financingTool_MirasconToGeico_financialPaid) <= pre_financingTool_GeicoFinancialToMirasconUpfront_paymentTotalReceived) {

                                    pre_financingTool_MirasconToGeicoFinancial_payable = premiumInclTaxPerInstallment * accountingPaymentPeriod;
                                pre_financingTool_MirasconToGeicoFinancial_payable = Math.round(pre_financingTool_MirasconToGeicoFinancial_payable*100.0)/100.0;
                                    jdbcDao.updateColumnDoubleValue_WithIdAndValue(id, 46, pre_financingTool_MirasconToGeico_financialPaid);
                            } else {
                                if ((premiumInclTaxPerInstallment * accountingPaymentPeriod + cancellations_RegisTGSLtoMirascon_payableGross + pre_financingTool_MirasconToGeico_financialPaid) > pre_financingTool_GeicoFinancialToMirasconUpfront_paymentTotalReceived) {
                                    pre_financingTool_MirasconToGeicoFinancial_payable = pre_financingTool_GeicoFinancialToMirasconUpfront_paymentTotalReceived - pre_financingTool_MirasconToGeico_financialPaid - pre_financingTool_MirasconToGeicoFinancialRefund_MTA;
                                    pre_financingTool_MirasconToGeicoFinancial_payable= Math.round(pre_financingTool_MirasconToGeicoFinancial_payable*100.0)/100.0;
                                    jdbcDao.updateColumnDoubleValue_WithIdAndValue(id, 46, pre_financingTool_MirasconToGeico_financialPaid);
                                }
                            }
                        }
                    }
                }
            }
        }
        return pre_financingTool_MirasconToGeicoFinancial_payable;
    }

    public double pre_financingTool_MirasconToGeico_financialPaid(int id) throws SQLException {
        System.out.println("Pre_financingTool_MirasconToGeico_financialPaid");
        accountingPaymentPeriod = jdbcDao.selectAccountingColumnIntValue_WithID(id, 13);
        if ((accountingPaymentTotal - accountingPaymentBegin - accountingPaymentPeriod) > 0) {
            pre_financingTool_MirasconToGeico_financialPaid = accountingPaymentTotal - accountingPaymentBegin - accountingPaymentPeriod * premiumInclTaxPerInstallment;
            System.out.println("Null Check : "+pre_financingTool_MirasconToGeico_financialPaid);
            pre_financingTool_MirasconToGeico_financialPaid = Math.round(pre_financingTool_MirasconToGeico_financialPaid*100.0)/100.0;
            jdbcDao.updateColumnDoubleValue_WithIdAndValue(id, 46, pre_financingTool_MirasconToGeico_financialPaid);
        } else {
            pre_financingTool_MirasconToGeico_financialPaid = v0_0;
            pre_financingTool_MirasconToGeico_financialPaid = Math.round(pre_financingTool_MirasconToGeico_financialPaid*100.0)/100.0;
            jdbcDao.updateColumnDoubleValue_WithIdAndValue(id, 46, pre_financingTool_MirasconToGeico_financialPaid);
        }
        return pre_financingTool_MirasconToGeico_financialPaid;
    }

    public double pre_financingTool_MirasconToGeicoFinancialRefund_MTA(int id) throws SQLException, ParseException {
        accountingPaymentRefund = jdbcDao.selectAccountingColumnDoubleValue_WithID(id, 19);
        refundDate = jdbcDao.selectColumnDateValue_WithID(id,39);
        if (refundDate != null) {
            if (ofInstallmentsPerYear == 12 && refundDate.compareTo(settlementPeriod_startDate) < 0) {
                pre_financingTool_MirasconToGeicoFinancialRefund_MTA = cancellationsRefundGross - accountingPaymentRefund;
                pre_financingTool_MirasconToGeicoFinancialRefund_MTA = Math.round(pre_financingTool_MirasconToGeicoFinancialRefund_MTA*100.0)/100.0;
                jdbcDao.updateColumnDoubleValue_WithIdAndValue(id, 47, pre_financingTool_MirasconToGeicoFinancialRefund_MTA);
            }
            else {
                pre_financingTool_MirasconToGeicoFinancialRefund_MTA = v0_0;
                pre_financingTool_MirasconToGeicoFinancialRefund_MTA = Math.round(pre_financingTool_MirasconToGeicoFinancialRefund_MTA*100.0)/100.0;
                jdbcDao.updateColumnDoubleValue_WithIdAndValue(id, 47, pre_financingTool_MirasconToGeicoFinancialRefund_MTA);
            }
        } else {
            pre_financingTool_MirasconToGeicoFinancialRefund_MTA = v0_0;
            pre_financingTool_MirasconToGeicoFinancialRefund_MTA = Math.round(pre_financingTool_MirasconToGeicoFinancialRefund_MTA*100.0)/100.0;
            jdbcDao.updateColumnDoubleValue_WithIdAndValue(id, 47, pre_financingTool_MirasconToGeicoFinancialRefund_MTA);
        }
        return pre_financingTool_MirasconToGeicoFinancialRefund_MTA;
    }

    public double pre_financingTool_MirasconToGeicoFinancialResidualDept(int id) throws SQLException {
        if ((pre_financingTool_GeicoFinancialToMirasconUpfront_paymentTotalReceived - pre_financingTool_MirasconToGeico_financialPaid - pre_financingTool_MirasconToGeicoFinancialRefund_MTA) < 0) {
            pre_financingTool_MirasconToGeicoFinancialResidualDept = v0_0;
            pre_financingTool_MirasconToGeicoFinancialResidualDept = Math.round(pre_financingTool_MirasconToGeicoFinancialResidualDept*100.0)/100.0;
            jdbcDao.updateColumnDoubleValue_WithIdAndValue(id, 48, pre_financingTool_MirasconToGeicoFinancialResidualDept);
        } else {
            if ((pre_financingTool_GeicoFinancialToMirasconUpfront_paymentTotalReceived - pre_financingTool_MirasconToGeico_financialPaid - pre_financingTool_MirasconToGeicoFinancialRefund_MTA) < 0) {
                pre_financingTool_MirasconToGeicoFinancialResidualDept = v0_0;
                pre_financingTool_MirasconToGeicoFinancialResidualDept = Math.round(pre_financingTool_MirasconToGeicoFinancialResidualDept*100.0)/100.0;
                jdbcDao.updateColumnDoubleValue_WithIdAndValue(id, 48, pre_financingTool_MirasconToGeicoFinancialResidualDept);
            } else {
                System.out.println("Check Null exception: 1. " +pre_financingTool_MirasconToGeicoFinancialResidualDept+" 2. "+pre_financingTool_GeicoFinancialToMirasconUpfront_paymentTotalReceived+" 3. "+pre_financingTool_MirasconToGeicoFinancial_payable+" 4. "+ pre_financingTool_MirasconToGeico_financialPaid+ " 5. "+ pre_financingTool_MirasconToGeicoFinancialRefund_MTA);
                pre_financingTool_MirasconToGeicoFinancialResidualDept = (((pre_financingTool_GeicoFinancialToMirasconUpfront_paymentTotalReceived) - pre_financingTool_MirasconToGeico_financialPaid) - pre_financingTool_MirasconToGeicoFinancialRefund_MTA);
                pre_financingTool_MirasconToGeicoFinancialResidualDept = Math.round(pre_financingTool_MirasconToGeicoFinancialResidualDept*100.0)/100.0;
                jdbcDao.updateColumnDoubleValue_WithIdAndValue(id, 48, pre_financingTool_MirasconToGeicoFinancialResidualDept);
            }
        }
        return pre_financingTool_MirasconToGeicoFinancialResidualDept;
    }

}