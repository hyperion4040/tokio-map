
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
 *         &lt;element name="y1" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="x1" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="y2" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="x2" type="{http://www.w3.org/2001/XMLSchema}float"/>
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

    protected float y1;
    protected float x1;
    protected float y2;
    protected float x2;

    /**
     * Gets the value of the y1 property.
     * 
     */
    public float getY1() {
        return y1;
    }

    /**
     * Sets the value of the y1 property.
     * 
     */
    public void setY1(float value) {
        this.y1 = value;
    }

    /**
     * Gets the value of the x1 property.
     * 
     */
    public float getX1() {
        return x1;
    }

    /**
     * Sets the value of the x1 property.
     * 
     */
    public void setX1(float value) {
        this.x1 = value;
    }

    /**
     * Gets the value of the y2 property.
     * 
     */
    public float getY2() {
        return y2;
    }

    /**
     * Sets the value of the y2 property.
     * 
     */
    public void setY2(float value) {
        this.y2 = value;
    }

    /**
     * Gets the value of the x2 property.
     * 
     */
    public float getX2() {
        return x2;
    }

    /**
     * Sets the value of the x2 property.
     * 
     */
    public void setX2(float value) {
        this.x2 = value;
    }

}
