package com.club.padel.exception.model;

public enum MSErrorSeverity {
	SEVERITY_ERROR(1),
	SEVERITY_WARNING(2),
	SEVERITY_INFO(3);

 private final Integer value;

	MSErrorSeverity(Integer v) {
     value = v;
 }

 public Integer value() {
     return value;
 }

 public static MSErrorSeverity fromValue(Integer v) {
     for (MSErrorSeverity c: MSErrorSeverity.values()) {
         if (c.value.equals(v)) {
             return c;
         }
     }
     throw new IllegalArgumentException();
 }
}