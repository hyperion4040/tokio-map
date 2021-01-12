
package com.stm.tokiomap.cityImage;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
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
 *         &lt;element name="image" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
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
    "image","minimap"
})
@XmlRootElement(name = "getImageResponse", namespace = "http://akozlowski/soap")
public class GetImageResponse {

    @XmlElement(required = true)
    protected byte[] image;

    @XmlElement(required = true)
    protected byte[] minimap;

    /**
     * Gets the value of the image property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getImage() {
        return image;
    }

    /**
     * Sets the value of the image property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setImage(byte[] value) {
        this.image = value;
    }

    /**
     * Gets the value of the minimap property.
     *
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getMinimap() {
        return minimap;
    }

    /**
     * Sets the value of the minimap property.
     *
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setMinimap(byte[] value) {
        this.minimap = value;
    }

}
