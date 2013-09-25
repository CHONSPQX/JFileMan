package com.github.jfileman.renderer;

import java.io.OutputStream;
import java.util.Map;

public interface HTMLRenderer
{
   public String render(String template, Map<String, ?> context) throws RendererException;
   public void render(String template, Map<String, ?> context, OutputStream out) throws RendererException;
}
