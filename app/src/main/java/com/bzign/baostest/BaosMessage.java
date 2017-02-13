package com.bzign.baostest;

import android.util.Property;

/**
 * Created by demae on 12/02/2017.
 */

public enum BaosMessage {
    GetServerItemReq                ((short)0x01, 6),
    SetServerItemReq                ((short)0x02, 6),
    GetDatapointDescriptionReq      ((short)0x03, 6),
    GetDescriptionStringReq         ((short)0x04, 6),
    GetDatapointValueReq            ((short)0x05, 6),
    SetDatapointValueReq            ((short)0x06, 6),
    GetParameterByteReq             ((short)0x07, 6),
    SetDatapointHistoryCommandReq   ((short)0x08, 6),
    GetDatapointHistoryStateReq     ((short)0x09, 6),
    GetDatapointHistoryReq          ((short)0x0A, 6),
    GetServerItemRes                ((short)0x81, -1),
    SetServerItemRes                ((short)0x82, -1),
    GetDatapointDescriptionRes      ((short)0x83, -1),
    GetDescriptionStringRes         ((short)0x84, -1),
    GetDatapointValueRes            ((short)0x85, -1),
    SetDatapointValueRes            ((short)0x86, -1),
    GetParameterByteRes             ((short)0x87, -1),
    SetDatapointHistoryCommandRes   ((short)0x88, -1),
    GetDatapointHistoryStateRes     ((short)0x89, -1),
    GetDatapointHistoryRes          ((short)0x8A, -1),
    ServerItemInd                   ((short)0xC2, -1),
    DatapointValueInd               ((short)0xC1, -1);

    public final short address;
    public final int size;

    BaosMessage(short address, int size){ this.address=address; this.size=size;}
};