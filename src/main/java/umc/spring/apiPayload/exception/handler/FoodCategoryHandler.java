package umc.spring.apiPayload.exception.handler;

import umc.spring.apiPayload.code.status.ErrorStatus;

public class FoodCategoryHandler extends RuntimeException {
    private final String errorCode;
    public FoodCategoryHandler(ErrorStatus errorStatus) {
        super(errorStatus.getMessage());
        this.errorCode = errorStatus.getCode();
    }
    public String getErrorCode() {
        return errorCode;
    }
}
