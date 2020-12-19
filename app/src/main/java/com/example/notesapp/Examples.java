package com.example.notesapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.util.Log.e;

public class Examples extends AppCompatActivity {
FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    private static final String TAG = "Examples";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_examples);
    }
    public void createDocument(View view)
    {
       // FirebaseFirestore.getInstance();
       /* Map<String,Object> map = new HashMap<>();
        map.put("text ", "Hello World");
        map.put("isCompleted",false);
        map.put("created",new Timestamp(new Date()));
        firestore.collection("notes")
                .add(map)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG,"onSuccess: task was Succesful");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG,"onFailure: task was unsuccesful");
                    }
                });*/
     /*  Map<String,Object> map = new HashMap<>();
       map.put("name","iPhone 11");
       map.put("price" , "699 Dollars");
       map.put("isAvailable",true);*/

     products product = new products("iPhone 11","699 dollars",true);
       firestore.collection("products")
            //   .add(map)
               .add(product)
               .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                   @Override
                   public void onSuccess(DocumentReference documentReference) {
                       Log.d(TAG,"onSuccess: task was Succesful");
                   }
               })
               .addOnFailureListener(new OnFailureListener() {
                   @Override
                   public void onFailure(@NonNull Exception e) {
                       Log.d(TAG,"onFailure: task was unsuccesful");
                   }
               });


    }

    public void readDocument(View view) {
       /* firestore.collection("products")       //.whereLessThan("price",1000) Like this any can be done before .get()
               .get()
               .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                   @Override
                   public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                       Log.d(TAG,"On Success: We are getting the data");
                      List<DocumentSnapshot> snapshotList =  queryDocumentSnapshots.getDocuments();
                      for (DocumentSnapshot snapshot: snapshotList)
                      {
                          Log.d(TAG,"onSuccess : "+snapshot.getData().toString());
                      }

                   }
               })
               .addOnFailureListener(new OnFailureListener() {
                   @Override
                   public void onFailure(@NonNull Exception e) {
                      Log.e(TAG,"On Failure",e);

                   }
               }); */
       firestore.collection("products")
               .document("7uQ4kw9HQ5leAJYwuCSA")
               .get()
               .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                   @Override
                   public void onSuccess(DocumentSnapshot documentSnapshot) {
                      /* Log.d(TAG,"On Success: "+ documentSnapshot.getData());
                       Log.d(TAG,"On Succes: "+ documentSnapshot.getString("name"));*/
                      products product = documentSnapshot.toObject(products.class);
                      Log.d(TAG,"onSuccess: "+product.toString());
                       Log.d(TAG,"onSuccess: "+product.getName());
                       Log.d(TAG,"onSuccess: "+product.getPrice());
                       Log.d(TAG,"onSuccess: "+product.getisAvailable());
                   }
               })
               .addOnFailureListener(new OnFailureListener() {
                   @Override
                   public void onFailure(@NonNull Exception e) {
                       e(TAG,"On Failure",e);
                   }
               });


    }

    public void updateDocument(View view) {
   final DocumentReference docRef=    FirebaseFirestore.getInstance()
                                             .collection("products")
                                             .document("NRdg4RT3S9yUw2AsiEn9");
    Map<String,Object> map = new HashMap<>();
    map.put("name","MacBook ");
 /*   docRef.update(map)
            .addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Log.d(TAG,"onSuccess: yay,updated the doc");
                }
            })
            .addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.e(TAG,"onFailure: ",e);
                }
            });*/
 docRef.set(map)
         .addOnSuccessListener(new OnSuccessListener<Void>() {
             @Override
             public void onSuccess(Void aVoid) {
                 Log.d(TAG, "onSuccess: yay, set the doc");
             }
         })
         .addOnFailureListener(new OnFailureListener() {
             @Override
             public void onFailure(@NonNull Exception e) {
                 Log.e(TAG,"onFailure: ",e);
             }
         });

    }

    public void deleteDocument(View view) {
     FirebaseFirestore.getInstance()
             .collection("products")
             .document("NRdg4RT3S9yUw2AsiEn9")
             .delete()
             .addOnSuccessListener(new OnSuccessListener<Void>() {
                 @Override
                 public void onSuccess(Void aVoid) {
                     Log.d(TAG,"onSuccess: Doc deleted");
                 }
             })
             .addOnFailureListener(new OnFailureListener() {
                 @Override
                 public void onFailure(@NonNull Exception e) {
                     Log.e(TAG,"onFailure: ",e);
                 }
             });
    }

    public void getAllDocuments(View view) {
        FirebaseFirestore.getInstance()
                .collection("products")
             //   .orderBy("price")
                .orderBy("price", Query.Direction.DESCENDING)
                .limit(2)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<products> product_list = queryDocumentSnapshots.toObjects(products.class);
                        Log.d(TAG,"onSuccess: "+ product_list.toString());
                   /*     List<DocumentSnapshot> snapshotList = queryDocumentSnapshots.getDocuments();
                        for (DocumentSnapshot snapshot: snapshotList)
                        {
                            Log.d(TAG, "On Success: " + snapshot.getData());
                        }*/
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e(TAG,"ON Failure :" + e);
                    }
                });
    }

    public void getAllDocumentsWithRealtimeUpdate(View view) {
      /*  FirebaseFirestore.getInstance()
                .collection("products")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if(error!=null)
                        {
                            Log.e(TAG,"OnEvent: ",error);
                            return;
                        }
                        if(value!=null)
                        { Log.d(TAG,"On Event: ...................");
                         /*   List<DocumentSnapshot> snapshotList = value.getDocuments();
                        for(DocumentSnapshot snapshot: snapshotList) {
                            Log.d(TAG, "onEvent: " + snapshot.getData());
                        }
                         List<DocumentChange> documentChangeList  = value.getDocumentChanges();
                         for(DocumentChange documentChange: documentChangeList)
                         {
                             Log.d(TAG, "onEvent: "+ documentChange.getDocument().getData());
                         }
                        }
                        else
                        {
                            Log.e(TAG,"onEvent: query snapshot was null");
                        }
                    }
                });*/
      FirebaseFirestore.getInstance()
              .collection("products")
              .document( "NRdg4RT3S9yUw2AsiEn9")
              .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                  @Override
                  public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                      if(error!=null)
                      {
                          Log.e(TAG,"onEvent: ",error);
                      }
                      if(value!=null)
                      {    Log.d(TAG,"onEvent: ................");
                          Log.d(TAG,"onEvent: "+value.getData());
                      }
                      else
                      {
                          Log.e(TAG,"onEvent: NULL" );
                      }
                  }
              });

    }
}