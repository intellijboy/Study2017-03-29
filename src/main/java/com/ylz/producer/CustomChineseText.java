package com.ylz.producer;

import com.google.code.kaptcha.BackgroundProducer;
import com.google.code.kaptcha.GimpyEngine;
import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.text.TextProducer;
import com.google.code.kaptcha.text.WordRenderer;
import com.google.code.kaptcha.util.Configurable;
import com.ylz.com.ylz.code.util.ChineseUtil;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.UnsupportedEncodingException;
import java.util.Random;

/**
 * Created by liuburu on 2017/3/28.
 */
public class CustomChineseText extends Configurable  implements Producer {
    private int width = 200;
    private int height = 50;
  //  private String[] simplifiedChineseTexts = new String[]{"包括焦点", "新道消点", "服分目搜", "索姓名電", "子郵件信", "主旨請回", "電子郵件", "給我所有", "討論區明", "發表新文", "章此討論", "區所有文", "章回主題", "樹瀏覽搜"};


    @Override
    public BufferedImage createImage(String text) {
        WordRenderer wordRenderer = this.getConfig().getWordRendererImpl();
        GimpyEngine gimpyEngine = this.getConfig().getObscurificatorImpl();
        BackgroundProducer backgroundProducer = this.getConfig().getBackgroundImpl();
        boolean isBorderDrawn = this.getConfig().isBorderDrawn();
        this.width = this.getConfig().getWidth();
        this.height = this.getConfig().getHeight();
        BufferedImage bi = wordRenderer.renderWord(text, this.width, this.height);
        bi = gimpyEngine.getDistortedImage(bi);
        bi = backgroundProducer.addBackground(bi);
        Graphics2D graphics = bi.createGraphics();
        return bi;
    }

    @Override
    public String createText() {
        int count  = this.getConfig().getTextProducerCharLength();//获取要生成的个数
        StringBuffer resultCode = new StringBuffer();
        for(int i=0;i<count;i++){
            resultCode.append(ChineseUtil.randGenerate());
        }
        return resultCode.toString();
    }
}
