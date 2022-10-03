package no.ssb.api.pojo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by rsa on 27.10.2016.
 */
@XmlRootElement(name="classificationItem")
@XmlAccessorType(XmlAccessType.FIELD)
public class ClassificationItem {

    @XmlElement
    private String code;

    @XmlElement
    private int level;

    @XmlElement
    private String name;

    @XmlElement
    private String shortName;

    @XmlElement
    private String notes;

    @XmlElement
    private String parentCode;

    private int utg = 0;



    public String getCode() {
        return code;
    }

    public int getLevel() {
        return level;
    }

    public String getName() {
        return name;
    }

    public String getShortName() {
        return shortName;
    }

    public String getNotes() {
        return notes;
    }

    public String getParentCode() {
        return parentCode;
    }

    public int getUtg() { return utg; }

    @Override
    public String toString() {
        return "ClassificationItem{" +
                "code='" + code + '\'' +
                ", level=" + level +
                ", name='" + name + '\'' +
                ", shortName='" + shortName + '\'' +
                ", notes='" + notes + '\'' +
                ", parentCode='" + parentCode + '\'' +
                ", utg=" + utg +
                '}';
    }
}
