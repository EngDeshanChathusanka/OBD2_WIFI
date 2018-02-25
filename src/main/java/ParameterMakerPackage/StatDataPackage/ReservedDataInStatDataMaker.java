package ParameterMakerPackage.StatDataPackage;

/**
 * Created by Deshan on 10/19/16.
 */
public class ReservedDataInStatDataMaker {
    private static final String[] dignoseProtocolStrArray={
            " unknown or tracker mode",
            "VPW (passage car)",
            "PWM (passage car)",
            "CAN11 (passage car)",
            "CAN29 (passage car)",
            "KWP2000 (passage car)",
            "KWP2000M (passage car)",
            "ISO9141 (passage car)",
            "",
            "",
            "J939(commercial vehicle)",
            "J1708(commercial vehicle)"
    };

    private static final String[] hardwareCodeStrArray={
            "216G",
            "213G",
            "213E",
            "213C",
            "218G",
            "213GD"
    };

    private static final String[] cellularModuleStrArray={
            "",
            "Quectel M35(GPRS)",
            "Telit GE-865(GPRS)",
            "Telit CE-910(CDMA)",
            "Telit HE-910(WCDMA)",
            "ZTE MC8332(2G CDMA)",
            "SIM800H(2G)",
            "SIM800L(2G)"

    };
    public static String getReservedData(String reservedDataStr){
        String reservedData=dignoseProtocolStrArray[Integer.parseInt(reservedDataStr.substring(0,2),16)];

        return reservedData;
    }
}
