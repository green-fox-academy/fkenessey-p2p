package com.greenfoxacademy.p2p.models.DTOs;

public class ErrorMessageDTO {

  private String errorMessage;

  public ErrorMessageDTO() {
  }

  public ErrorMessageDTO(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  public String getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }
}
