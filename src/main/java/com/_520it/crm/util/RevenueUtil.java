package com._520it.crm.util;

import lombok.Getter;
import lombok.Setter;

/**
 * @author lyhzzz
 * @date 2017/11/10
 */

/**
 * 税收工具类
 */
@Getter@Setter
public class RevenueUtil {
    private double totalSalary = 0;  //总工资工资
    private double afterSalary=0 ;  //税后工资
    
    public  double getAfterSalary() {

        double tax = 0;
        double rate = 0;  // 税率
        double afterSalary = 0; // 税后工资
        if (totalSalary < 3500) { // 总收入为3500元以下，则免征税
            rate = 0;
        } else if (totalSalary >= 4000 && totalSalary < 4500) {
            rate = 0.0038;
        } else if (totalSalary >= 4500 && totalSalary < 5000) {
            rate = 0.0067;
        } else if (totalSalary >= 5000 && totalSalary < 6000) {
            rate = 0.0090;
        } else if (totalSalary >= 6000 && totalSalary < 8000) {
            rate = 0.0242;
        } else if (totalSalary >= 8000 && totalSalary < 10000) {
            rate = 0.0431;
        } else if (totalSalary >= 10000 && totalSalary < 15000) {
            rate = 0.0745;
        } else if (totalSalary >= 15000 && totalSalary < 20000) {
            rate = 0.1247;
        } else if (totalSalary >= 20000 && totalSalary < 30000) {
            rate = 0.1560;
        } else if (totalSalary >= 30000 && totalSalary < 38600) {
            rate = 0.1873;
        } else if (totalSalary >= 38600 && totalSalary < 40000) {
            rate = 0.2014;
        } else if (totalSalary >= 40000 && totalSalary < 50000) {
            rate = 0.2049;
        } else if (totalSalary >= 50000 && totalSalary < 60000) {
            rate = 0.2239;
        } else if (totalSalary >= 60000 && totalSalary < 70000) {
            rate = 0.2378;
        } else if (totalSalary >= 70000 && totalSalary < 80000) {
            rate = 0.2539;
        } else if (totalSalary >= 80000 && totalSalary < 90000) {
            rate = 0.2659;
        } else if (totalSalary >= 90000 && totalSalary < 100000) {
            rate = 0.2824;
        } else if (totalSalary >= 90000 && totalSalary < 100000) {
            rate = 0.2992;
        }else if(totalSalary >=22000){
            rate = 0.45;
        }
        tax = totalSalary * rate; // 计算税收
        afterSalary = totalSalary - tax; // 计算税后工资
        return afterSalary;
    }
}
