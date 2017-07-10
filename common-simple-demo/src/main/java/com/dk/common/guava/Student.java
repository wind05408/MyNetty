package com.dk.common.guava;

import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Ordering;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

/**
 * Created with IntelliJ IDEA
 * Student
 *
 * @author dk
 * @date 2017/7/10 15:45
 */
public class Student implements Comparable<Student> {
    private String lastName;
    private String firstName;
    private int zipCode;

    //JDK compareTo
//    @Override
//    public int compareTo(Student other) {
//        int cmp =lastName.compareTo(other.lastName);
//        if (cmp != 0) {
//            return cmp;
//        }
//        cmp = firstName.compareTo(other.firstName);
//        if (cmp != 0) {
//            return cmp;
//        }
//        return Integer.compare(zipCode, other.zipCode);
//    }

    //Guava compareTo
    @Override
    public int compareTo(Student other) {
        return ComparisonChain.start()
                .compare(this.lastName,other.lastName)
                .compare(this.firstName,other.firstName)
                .compare(this.zipCode,other.zipCode, Ordering.natural().nullsLast())
                .result();
    }

}
