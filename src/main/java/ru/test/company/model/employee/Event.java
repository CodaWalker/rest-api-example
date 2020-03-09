package ru.test.company.model.employee;


/**
 * Событие связанное с пребыванием или отстутсвием сотрудника
 */
public enum Event {
    PRESENCE_AT_WORK,
    ABSENTED_MEDICAL, //Отсутсивие по медицинским причинам
    ABSENTED_HOLIDAY, //Отсутсвие из за отпуска
    ABSENTED_OTHER //Отсутсиве по неизвестным причинам
}
