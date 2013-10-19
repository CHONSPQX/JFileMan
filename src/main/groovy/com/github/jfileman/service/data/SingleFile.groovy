package com.github.jfileman.service.data

import groovy.transform.ToString

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement

@ToString
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
class SingleFile implements Serializable
{
   @XmlElement String path
   @XmlElement Date creationDate
}
