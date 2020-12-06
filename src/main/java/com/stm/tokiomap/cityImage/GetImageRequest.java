
package com.stm.tokiomap.cityImage;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="y1" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="x1" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="y2" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="x2" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "y1",
    "x1",
    "y2",
    "x2"
})
@XmlRootElement(name = "getImageRequest", namespace = "http://akozlowski/soap")
public class GetImageRequest {

    protected int y1;
    protected int x1;
    protected int y2;
    protected int x2;

    /**
     * Gets the value of the y1 property.
     * 
     */
    public int getY1() {
        return y1;
    }

    /**
     * Sets the value of the y1 property.
     * 
     */
    public void setY1(int value) {
        this.y1 = value;
    }

    /**
     * Gets the value of the x1 property.
     * 
     */
    public int getX1() {
        return x1;
    }

    /**
     * Sets the value of the x1 property.
     * 
     */
    public void setX1(int value) {
        this.x1 = value;
    }

    /**
     * Gets the value of the y2 property.
     * 
     */
    public int getY2() {
        return y2;
    }

    /**
     * Sets the value of the y2 property.
     * 
     */
    public void setY2(int value) {
        this.y2 = value;
    }

    /**
     * Gets the value of the x2 property.
     * 
     */
    public int getX2() {
        return x2;
    }

    /**
     * Sets the value of the x2 property.
     * 
     */
    public void setX2(int value) {
        this.x2 = value;
    }

}
