package no.ssb.api.pojo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by rsa on 27.10.2016.
 */
@XmlRootElement(name="classificationItems")
@XmlAccessorType(XmlAccessType.FIELD)
public class ClassificationItems {

    @XmlElement(name = "classificationItem")
    private List<ClassificationItem> classificationItems = null;

    public List<ClassificationItem> getClassificationItems() {
        return classificationItems;
    }

    public void setClassificationItems(List<ClassificationItem> classificationItems) {
        this.classificationItems = classificationItems;
    }

    @Override
    public String toString() {
        return "ClassificationItems{" +
                "classificationItems=" + classificationItems +
                '}';
    }
}
