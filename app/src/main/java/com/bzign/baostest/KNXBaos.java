package com.bzign.baostest;

/**
 * Created by demae on 12/02/2017.
 */


public class KNXBaos {

    public KNXBaos(){};

    public short[] Encapsulate(short[] message)
    {
        int _orgLength = message.length;
        int _length= _orgLength+10;
        short[] _data = new short[_length];
        _data[0]=0x06;
        _data[1]=0x20;
        _data[2]=0xF0;
        _data[3]=0x80;
        _data[4]=(short)((_orgLength+4)>>8);
        _data[5]=(short)_orgLength;
        _data[6]=0x04;
        _data[7]=0x00;
        _data[8]=0x00;
        _data[9]=0x00;
        for (int _index=0; _index<_orgLength; _index++) _data[_index+10]=message[_index];
        return _data;
    }
}
