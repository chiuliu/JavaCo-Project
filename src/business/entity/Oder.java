package business.entity;

import business.constants.OderStatus;

import java.io.Serializable;
import java.util.Date;

public class Oder implements Serializable {
    private int orderId;
    private String serialNumber;
    private Users users;
    private double totalPrice;
    private OderStatus status;
    private String note;
    private String receiveName;
    private String receiveAddress;
    private String receivePhone;
    private Date created;
    private Date received;
}
