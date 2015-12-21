package app.phipex.com.recordando.Util;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Checkable;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sony vaio on 21/12/2015.
 */
public class CheckableRelativeLayout extends RelativeLayout implements Checkable{
    private boolean isChecked;
    private List<Checkable> checkableView;


    public CheckableRelativeLayout(Context context) {
        super(context);
        inicilizar(null);
    }

    public CheckableRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        inicilizar(attrs);
    }

    public CheckableRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inicilizar(attrs);
    }

    private void inicilizar(AttributeSet attrs) {
        this.isChecked = false;
        this.checkableView = new ArrayList<Checkable>();
    }

    //<editor-fold desc="Metodos para checkeables">
    @Override
    public void setChecked(boolean isChecked) {
        this.isChecked = isChecked;
        for (Checkable c: checkableView){
            c.setChecked(isChecked);
        }
    }

    @Override
    public boolean isChecked() {
        return isChecked;
    }

    @Override
    public void toggle() {
        this.isChecked = !this.isChecked;
        for (Checkable c: checkableView){
            c.toggle();
        }
    }
    //</editor-fold>


    @Override
    protected void onFinishInflate() {//cuando se termina de mostrar
        super.onFinishInflate();
        final  int childCount = this.getChildCount();//cuantos hijos tiene
        for (int i = 0; i < childCount; i++) {
             buscarComponentesCheckable(this.getChildAt(i));

        }
    }

    private void buscarComponentesCheckable(View view) {
        if(view instanceof Checkable){
            this.checkableView.add((Checkable) view);
        }
        if (view instanceof ViewGroup){
            final ViewGroup vg = (ViewGroup) view;
            final int childCount = vg.getChildCount();
            for (int i = 0; i < childCount; i++) {
                buscarComponentesCheckable(vg.getChildAt(i));

            }

        }
    }
}
