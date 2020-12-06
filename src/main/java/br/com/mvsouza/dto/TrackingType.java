package br.com.mvsouza.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TrackingType {

    SHIPMENT_NUMBER("0"), COLLECTION_ORDER("3"), TRACKING_NUMBER("7");

    private final String comboValue;

}
