package ru.test.company.service.position.argument;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PositionCreateArgument {
    private final String name;


    public PositionCreateArgument(String name) {

//       Validator.validateObjectParam(name, EmployeeError.EMPLOYEE_FIRST_NAME_IS_MANDATORY);
//       Validator.validateObjectParam(description, TicketError.TICKET_DESCRIPTION_IS_MANDATORY);

        this.name = name;

    }

}

