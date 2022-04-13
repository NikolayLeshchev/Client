package by.palto.command.factory;

import by.palto.command.Command;

import by.palto.command.CommandEnum;
import by.palto.command.impl.*;
import by.palto.command.impl.client.*;
import by.palto.command.impl.credit.AddNewCreditContractCommandImpl;
import by.palto.command.impl.credit.GetAllCreditTypesNameCommandImpl;
import by.palto.command.impl.credit.GetCreditTypeIdByNameCommandImpl;
import by.palto.command.impl.credit.GetPercentByCreditTypeCommandImpl;
import by.palto.command.impl.currency.GetAllCurrencyNameCommandImpl;
import by.palto.command.impl.currency.GetCurrencyIdByNameCommandImpl;
import by.palto.command.impl.deposit.AddNewDepositContactCommandImpl;
import by.palto.command.impl.deposit.GetAllDepositTypesNameCommandImpl;
import by.palto.command.impl.deposit.GetDepositTypeIdByNameCommandImpl;
import by.palto.command.impl.deposit.GetPercentByDepositTypeCommandImpl;

public class CommandFactory {


    private static final CommandFactory instance = new CommandFactory();

    public static CommandFactory getInstance() {
        return instance;
    }

    private CommandFactory() {

    }


    public Command getCommand(CommandEnum command) {

        switch (command) {
//            case "doAction":
//                return new DoActionCommandImpl();
            case ADD_NEW_USER:
                return new AddNewClientCommandImpl();
            case GET_ALL_CLIENTS:
                return new GetAllClientsCommandImpl();
            case GET_ID_CLIENT_BY_PERS_NUMB:
                return new GetClientIdByPersNumberCommandImpl();
            case DELETE_CLIENT:
                return new DeleteClientCommandImpl();
            case UPDATE_CLIENT:
                return new UpdateClientCommandImpl();

            case GET_ALL_CHARTS_NAMES:
                return new GetAllChartsCommandImpl();
            case GET_ID_CHART_BY_NAME:
                return new GetChartIDByNameCommandImpl();

            case GET_ALL_CURRENCY_NAMES:
                return new GetAllCurrencyNameCommandImpl();
            case GET_CURRENCY_ID_BY_NAME:
                return new GetCurrencyIdByNameCommandImpl();

            case GET_ID_DEPOSIT_TYPE_BY_NAME:
                return new GetDepositTypeIdByNameCommandImpl();
            case GET_ALL_DEPOSIT_TYPES_NAMES:
                return new GetAllDepositTypesNameCommandImpl();
            case GET_PERCENT_BY_DEPOSIT_TYPE:
                return new GetPercentByDepositTypeCommandImpl();
            case ADD_NEW_DEPOSIT_CONTRACT:
                return new AddNewDepositContactCommandImpl();

            case GET_ALL_CREDIT_TYPES_NAMES:
                return new GetAllCreditTypesNameCommandImpl();
            case GET_PERCENT_BY_CREDIT_TYPE:
                return new GetPercentByCreditTypeCommandImpl();
            case GET_ID_CREDIT_TYPE_BY_NAME:
                return new GetCreditTypeIdByNameCommandImpl();
            case ADD_NEW_CREDIT_CONTRACT:
                return new AddNewCreditContractCommandImpl();


            case END_OF_THE_DAY:
                return new EndOfTheDayCommandImpl();

        }
        return null;//exception
    }
}
