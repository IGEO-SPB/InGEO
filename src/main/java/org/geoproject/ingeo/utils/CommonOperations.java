package com.geoproject.igeo.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CommonOperations {

    public static float round(float number, int precision) {
        MathContext context = new MathContext(precision, RoundingMode.HALF_UP);
        BigDecimal result;
        try {
            result = new BigDecimal(number, context);
        } catch (RuntimeException exception) {
            exception.printStackTrace();

            return 1;
        }

        return result.floatValue();
    }


    public static float roundH(double z, int i) {
        if (z == 0) {
            return 0;
        }

        double floatPart = z - (int) z;
        double floatI = (int) (floatPart * Math.pow(10, i + 1)) - (int) (floatPart * Math.pow(10, i)) * 10;

        double result = (int) z + (int) (floatPart * Math.pow(10, i)) / Math.pow(10, i);

        if (floatI >= 5) {
            result += Math.pow(10, -i);
        }

        return (float) result;
    }

//    Function RoundH(z, i)
//    If z = 0 Then
//            RoundH = 0
//    GoTo e1
//    End If
//'If i = 0 Then
//        'round = Empty
//        'GoTo e1
//        'End If
//
//    Dim FLOAT As Single
//    Dim floati As Single  'i+1 number at float(z)
//            'If z = IsNull Then       'vas
//'z = 0
//        'End If
//    Dim s As Variant
//    s = IsNull(z)
//    If s = True Then
//    GoTo e1
//
//    End If
//
//
//    z = z + 10 ^ (-10)
//
//    FLOAT = z - Int(z)
//    floati = Int(FLOAT * 10 ^ (i + 1)) - Int(FLOAT * 10 ^ i) * 10
//
//
//    RoundH = Int(z) + Int((FLOAT) * 10 ^ i) / 10 ^ i
//
//    If (floati >= 5) Then
//    RoundH = RoundH + 10 ^ (-i)
//    End If
//    tp1:
//    e1:
//
//    End Function
}
