package com.kanper.handler;

import com.kanper.common.ActionResult;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = DataIntegrityViolationException.class)
    @ResponseBody
    public ActionResult DataIntegrityViolationException(HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        return ActionResult.fail("数据库已经存在该记录");
    }

    @ExceptionHandler(value = MySQLIntegrityConstraintViolationException.class)
    @ResponseBody
    public ActionResult MySQLIntegrityConstraintViolationException(HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        return ActionResult.fail("会员有购买记录不能删除");
    }
}
