package com.example.practica14_controlp3;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class SelectColor extends View {

    private int alpha = 128;
    private int red = 0;
    private int green = 0;
    private int blue = 0;

    private static int incremento = 10;
    private static int decremento = 10;

    public SelectColor(Context context){
        super(context);
    }

    public SelectColor(Context context, AttributeSet attrs){
        super(context, attrs);
    }

    public SelectColor(Context context, AttributeSet attrs, int defStyleAttr){
        super(context, attrs, defStyleAttr);
    }

    public SelectColor(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes){
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
        int ancho = calcularAncho(widthMeasureSpec);
        int alto = calcularAlto(heightMeasureSpec);

        setMeasuredDimension(ancho, alto);
    }

    private int calcularAncho(int ancho){
        int res = 100;
        int modo = MeasureSpec.getMode(ancho);
        int limite = MeasureSpec.getSize(ancho);
        if(modo == MeasureSpec.AT_MOST || modo == MeasureSpec.EXACTLY){
            res = limite;
        }
        return res;
    }

    private int calcularAlto(int alto){
        int res = 100;
        int modo = MeasureSpec.getMode(alto);
        int limite = MeasureSpec.getSize(alto);
        if(modo == MeasureSpec.AT_MOST || modo == MeasureSpec.EXACTLY){
            res = limite;
        }
        return res;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        float alto = getMeasuredHeight();
        float ancho = getMeasuredWidth();


        Paint pRelleno = new Paint();
        pRelleno.setStyle(Paint.Style.FILL);

        // Gris para indicar alpha parte izquierda
        // Alpha alto
        pRelleno.setColor(Color.argb(255, 128, 128, 128));
        canvas.drawRect(0f, 0f, ancho / 4f, alto / 4f, pRelleno);
        // Alpha bajo
        pRelleno.setColor(Color.argb(128, 128, 128, 128));
        canvas.drawRect(0f, alto / 4f, ancho / 4f, (alto / 4f)*2, pRelleno);

        // Rojo parte media izquierda
        // Rojo alto
        pRelleno.setColor(Color.rgb(255, 0, 0));
        canvas.drawRect(ancho / 4f, 0f, (ancho / 4f)*2, alto / 4f, pRelleno);
        // Rojo bajo
        pRelleno.setColor(Color.rgb(128, 0, 0));
        canvas.drawRect(ancho / 4f, alto / 4f, (ancho / 4f)*2, (alto / 4f)*2, pRelleno);

        // Verde parte media derecha
        // Verde alto
        pRelleno.setColor(Color.rgb(0, 255, 0));
        canvas.drawRect((ancho / 4f)*2, 0f, (ancho / 4f)*3, alto / 4f, pRelleno);
        // Verde bajo
        pRelleno.setColor(Color.rgb(0, 128, 0));
        canvas.drawRect((ancho / 4f)*2, alto / 4f, (ancho / 4f)*3, (alto / 4f)*2, pRelleno);

        // Azul parte derecha
        // Azul alto
        pRelleno.setColor(Color.rgb(0, 0, 255));
        canvas.drawRect((ancho / 4f)*3, 0f, ancho, alto / 4f, pRelleno);
        // Azul bajo
        pRelleno.setColor(Color.rgb(0, 0, 128));
        canvas.drawRect((ancho / 4f)*3, alto / 4f, ancho, (alto / 4f)*2, pRelleno);

        // Rectángulo inferior con el color dinámico
        pRelleno.setColor(Color.argb(alpha, red, green, blue)); // Color que cambia con el toque
        canvas.drawRect(0, alto / 2f, ancho, alto, pRelleno);

        // Bordes de control
        Paint pBorde = new Paint();
        pBorde.setStyle(Paint.Style.STROKE);
        pBorde.setColor(Color.BLACK);
        canvas.drawRect(0, 0, ancho - 1, alto - 1, pBorde);
    }


    private OnSelectedColorListener listener = null;

    @Override
    public boolean onTouchEvent(MotionEvent event){
        // Condicon para acceder a los colores de la parte superiror
        if(event.getY()>0 && event.getY()<getMeasuredHeight()/4){

            if(event.getX()>0 && event.getX()<getMeasuredWidth()){

                // Actualizar color azul positvo
                if(event.getX()>(getMeasuredWidth()/4)*3){
                    blue+=incremento;
                    if(blue>255){
                        blue=255;
                    }
                }
                // Actualizar color verde postivo
                if(event.getX()>getMeasuredWidth()/2 && event.getX()<(getMeasuredWidth()/4)*3){
                    green+=incremento;
                    if(green>255){
                        green=255;
                    }
                }
                // Actualizar color rojo positivo
                if(event.getX()>getMeasuredWidth()/4 && event.getX()<getMeasuredWidth()/2){
                    red+=incremento;
                    if(red>255){
                        red=255;
                    }
                }
                // Actualizar alpha positivo
                if(event.getX()>0 && event.getX()<getMeasuredWidth()/4){
                    alpha+=incremento;
                    if(alpha>255){
                        alpha=255;
                    }
                }
                //Refrescamos la pagina
                this.invalidate();
            }

        }

        // Condicon para acceder a los colores de la parte inferior
        if(event.getY()>getMeasuredHeight()/4 && event.getY() < getMeasuredHeight()/2){
            if(event.getX()>0 && event.getX()<getMeasuredWidth()){

                // Actualizar color azul negativo
                if(event.getX()>(getMeasuredWidth()/4)*3){
                    blue-=decremento;
                    if(blue<0){
                        blue=0;
                    }
                }
                // Actualizar color verde negativo
                if(event.getX()>getMeasuredWidth()/2 && event.getX()<(getMeasuredWidth()/4)*3){
                    green-=decremento;
                    if(green<0){
                        green=0;
                    }
                }
                // Actualizar color rojo negativo
                if(event.getX()>getMeasuredWidth()/4 && event.getX()<getMeasuredWidth()/2){
                    red-=decremento;
                    if(red<0){
                        red=0;
                    }
                }
                // Actualizar alpha negativo
                if(event.getX()>0 && event.getX()<getMeasuredWidth()/4){
                    alpha-=decremento;
                    if(alpha<0){
                        alpha=0;
                    }
                }

                //Refrescamos la pagina
                this.invalidate();
            }
        }

        // Condicon para mandar a llamar al listner con los colores seleccionados
        if(event.getY()>getMeasuredHeight()/2 && event.getY() < getMeasuredHeight()) {
            listener.onSelectedColor(alpha, red, green, blue);
        }

        return super.onTouchEvent(event);
    }

    public void setOnColorListener(OnSelectedColorListener l){
        listener = l;
    }

}
