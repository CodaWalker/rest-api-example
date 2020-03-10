package ru.test.company.error.api;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.test.company.error.ErrorCustom;
import ru.test.company.error.dto.ErrorDTO;

import javax.persistence.EntityNotFoundException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class ApiExceptionHandler  {
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {ConstraintViolationException.class})
    public ErrorDTO handleResourceNotFoundException(ConstraintViolationException e) {
        return new ErrorDTO(400, "Ошибка параметров запроса");
    }

    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {BindException.class})
    public ErrorDTO parameterBindException(BindException e) {
        return new ErrorDTO(400, "Некорректный формат одного из параметров запроса");
    }

    @ResponseBody
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = {ErrorCustom.class})
    public ErrorDTO parameterBindCustomException(ErrorCustom e) {
        if(e.getCode() == 1) {
            return new ErrorDTO(400, "Этот сотрудник уволен ранее");
        }else
        if(e.getCode() == 2) {
            return new ErrorDTO(400, "Этот сотрудник не уволен, необходимо предварительно его уволить");
        }else
        if(e.getCode() == 3) {
            return new ErrorDTO(400, "Не найден сотрудник");
        }else
        if(e.getCode() == 4) {
            return new ErrorDTO(400, "Не правильно введены значения даты! Формат: yyyy-MM-dd ");
        }else
        if(e.getCode() == 5) {
            return new ErrorDTO(400, "Неизвестная ошибка");
        }else
        if(e.getCode() == 6) {
            return new ErrorDTO(400, "У сотрудника нет событий");
        }else
            return new ErrorDTO(400, "Неизвестная ошибка");
    }

    @ResponseBody
    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public ErrorDTO processEntityNotFoundException(EntityNotFoundException exception) {
        return new ErrorDTO(1000, exception.getMessage());
    }

}
