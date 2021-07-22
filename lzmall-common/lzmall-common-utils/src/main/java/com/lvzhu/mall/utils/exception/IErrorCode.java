package com.lvzhu.mall.utils.exception;

public interface IErrorCode {
  /**
   * 错误码
   *
   * @return
   */
  public int getCode();

  /**
   * 错误消息
   *
   * @return
   */
  public String getMessage();
}