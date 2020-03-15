package io.github.towerking.springbootsimplemvc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author wangwei
 * @date 2020/03/15 18:02
 */

@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
public class MyException extends RuntimeException {

}
