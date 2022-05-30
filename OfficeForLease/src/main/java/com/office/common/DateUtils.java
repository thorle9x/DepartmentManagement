/**
 * 
 */
package com.office.common;

import java.sql.Timestamp;

public class DateUtils {
  
  public static Timestamp getCurrentTimestamp() {
    return new Timestamp(System.currentTimeMillis());
  }

}