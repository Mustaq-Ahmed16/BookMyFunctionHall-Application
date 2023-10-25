package com.example.smartwed;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.ar.core.Anchor;
import com.google.ar.core.HitResult;
import com.google.ar.core.Plane;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.math.Vector3;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.rendering.ViewRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.BaseArFragment;
import com.google.ar.sceneform.ux.TransformableNode;

public class new_designs extends AppCompatActivity implements View.OnClickListener {
    ArFragment arFragment;
    private ModelRenderable flowerrenderable,armchairrenderable,lamprenderable,modelrenderable,roomrenderable,tablerenderable,
            stairrenderable;
    ImageView sofa,tabl,roses,model,room,table,tv;
    View arrayView[];

    int selected=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_designs);

        arFragment=(ArFragment)getSupportFragmentManager().findFragmentById(R.id.Sceneform_ux_fragment);
        sofa= findViewById(R.id.chair);
        tabl=findViewById(R.id.cloth);
        roses=findViewById(R.id.lamp);
        model=findViewById(R.id.model);
        room=findViewById(R.id.room);
        table=findViewById(R.id.table);
        tv=findViewById(R.id.tv);
        setArrayView();
        setClickListner();
        setupModel();

        arFragment.setOnTapArPlaneListener(new BaseArFragment.OnTapArPlaneListener() {
            @Override
            public void onTapPlane(HitResult hitResult, Plane plane, MotionEvent motionEvent) {

                Anchor anchor=hitResult.createAnchor();
                AnchorNode anchorNode=new AnchorNode(anchor);
                anchorNode.setParent(arFragment.getArSceneView().getScene());
                createModel(anchorNode,selected);
            }

        });
    }

    private void setupModel() {

        ModelRenderable.builder()
                .setSource(this,R.raw.armchair)
                .build().thenAccept(renderable ->armchairrenderable=renderable )
                .exceptionally(throwable -> {
                    Toast.makeText(this,"Model can't be loaded", Toast.LENGTH_SHORT).show();
                    return null;
                });
        ModelRenderable.builder()
                .setSource(this,R.raw.flowers)
                .build().thenAccept(renderable ->flowerrenderable=renderable )
                .exceptionally(throwable -> {
                    Toast.makeText(this,"Model can't be loaded", Toast.LENGTH_SHORT).show();
                    return null;
                });
        ModelRenderable.builder()
                .setSource(this,R.raw.model)
                .build().thenAccept(renderable ->modelrenderable=renderable )
                .exceptionally(throwable -> {
                    Toast.makeText(this,"Model can't be loaded", Toast.LENGTH_SHORT).show();
                    return null;
                });
        ModelRenderable.builder()
                .setSource(this,R.raw.stairs)
                .build().thenAccept(renderable ->stairrenderable=renderable )
                .exceptionally(throwable -> {
                    Toast.makeText(this,"Model can't be loaded", Toast.LENGTH_SHORT).show();
                    return null;
                });

    }

    private void createModel(AnchorNode anchorNode, int selected) {
        if(selected==1)
        {
            TransformableNode armchair= new TransformableNode(arFragment.getTransformationSystem());
            armchair.setParent(anchorNode);
            armchair.setRenderable(armchairrenderable);
            armchair.select();
            addName(anchorNode,armchair,"sofa chair");
        }
        if(selected==2)
        {
            TransformableNode armchair= new TransformableNode(arFragment.getTransformationSystem());
            armchair.setParent(anchorNode);
            armchair.setRenderable(flowerrenderable);
            armchair.select();
            addName(anchorNode,armchair,"flowers");
        }
        if(selected==3)
        {
            TransformableNode armchair= new TransformableNode(arFragment.getTransformationSystem());
            armchair.setParent(anchorNode);
            armchair.setRenderable(modelrenderable);
            armchair.select();
            addName(anchorNode,armchair,"sofa");
        }
        if(selected==4)
        {
            TransformableNode armchair= new TransformableNode(arFragment.getTransformationSystem());
            armchair.setParent(anchorNode);
            armchair.setRenderable(stairrenderable);
            armchair.select();
            addName(anchorNode,armchair,"stairs");
        }
    }

    private void addName(AnchorNode anchorNode, TransformableNode model, String name) {

        ViewRenderable.builder()
                .setView(this,R.layout.names_items)
                .build()
                .thenAccept(viewRenderable->{

                    TransformableNode nameView= new TransformableNode(arFragment.getTransformationSystem());
                    nameView.setLocalPosition(new Vector3(0f,model.getLocalPosition().y+0.5f,0));
                    nameView.setParent(anchorNode);
                    nameView.setRenderable(viewRenderable);
                    nameView.select();
                    TextView txt_name=(TextView) viewRenderable.getView();
                    txt_name.setText(name);
                    txt_name.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            anchorNode.setParent(null);
                        }
                    });

                });




    }

    private void setClickListner() {
        for (int i = 0; i < arrayView.length; i++)
            arrayView[i].setOnClickListener(this);
    }

    private void setArrayView() {
        arrayView=new View[]{
                sofa,tabl,roses,model,room,table,tv
        };
    }
    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.chair) {
            selected = 1;
            setBackground (v.getId());
        }
        else if(v.getId()==R.id.cloth){
            selected=2;
            setBackground (v.getId());
        }
        else if(v.getId()==R.id.lamp) {
            selected = 3;
            setBackground (v.getId());
        }
        else if(v.getId()==R.id.model) {
            selected = 4;
            setBackground (v.getId());
        }
        else if(v.getId()==R.id.room) {
            selected = 5;
            setBackground (v.getId());
        }
        else if(v.getId()==R.id.table) {
            selected = 6;
            setBackground(v.getId());
        }
        else if(v.getId()==R.id.tv) {
            selected = 7;
            setBackground(v.getId());
        }

    }

    private void setBackground(int id){
        for(int j=0; j<arrayView.length; j++){
            if (arrayView[j].getId()==id)
                arrayView[j].setBackgroundColor(Color.parseColor("#80333639"));
            else
                arrayView[j].setBackgroundColor(Color.TRANSPARENT);

        }

    }



}