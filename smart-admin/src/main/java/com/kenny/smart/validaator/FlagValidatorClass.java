package com.kenny.smart.validaator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * ClassName: FlagValidatorClass
 * Function:  状态标记校验器
 * Date:      2019/9/26 13:16
 * @author Kenny
 * version    V1.0
 */
public class FlagValidatorClass implements ConstraintValidator<FlagValidator, Integer> {

    private String[] values;
    @Override
    public void initialize(FlagValidator constraintAnnotation) {
        this.values = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(Integer integer, ConstraintValidatorContext constraintValidatorContext) {
        boolean isValid = false;
        if (integer == null) {
            //当前状态为空时使用默认值
            return true;
        }
        for (int i = 0; i < values.length; i++) {
            if (values[i].equals(String.valueOf(integer))) {
                isValid = true;
                break;
            }
        }
        return isValid;
    }
}
