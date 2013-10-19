package com.github.jfileman.service.data

import groovy.transform.ToString;

import java.io.Serializable

import javax.xml.bind.annotation.XmlRootElement;

@ToString
@XmlRootElement
class SingleFile implements Serializable
{
   String path
   Date creationDate
}
