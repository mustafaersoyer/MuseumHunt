package com.example.museumhunt.ui.tour;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.museumhunt.Adapters.SingleArtifactsAdapter;
import com.example.museumhunt.Model.Artifacts;
import com.example.museumhunt.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.Identifier;
import org.altbeacon.beacon.MonitorNotifier;
import org.altbeacon.beacon.RangeNotifier;
import org.altbeacon.beacon.Region;

import java.util.Collection;

public class TourFragment extends Fragment implements BeaconConsumer {

    private TourViewModel tourViewModel;
    SingleArtifactsAdapter adapter;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_tour, container, false);
        FloatingActionButton floatingActionButton = root.findViewById(R.id.fab);

        final RecyclerView recyclerView;

        recyclerView = root.findViewById(R.id.recyclerViewTour);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        beaconSet();
        getPermission();

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startBeaconMonitoring();
                tourViewModel =
                        ViewModelProviders.of(getActivity()).get(TourViewModel.class);
                Toast.makeText(getContext(), "Start Tour", Toast.LENGTH_SHORT).show();

                tourViewModel.getArtifacts().observe(getActivity(), new Observer<Artifacts>() {
                    @Override
                    public void onChanged(Artifacts artifacts) {
                        adapter = new SingleArtifactsAdapter(getContext(), artifacts);
                        recyclerView.setAdapter(adapter);
                    }

                });
            }
        });

        return root;
    }


    private Boolean entryMessageRaised = false;
    private Boolean exitMessageRaised = false;
    private Boolean rangingMessageRaised = false;
    protected static final String TAG = "MonitoringActivity";
    private BeaconManager beaconManager=null;
    private Region beaconRegion;

    void beaconSet(){
        String iBeaconLayout = "m:2-3=0215,i:4-19,i:20-21,i:22-23,p:24-24";
        beaconManager = BeaconManager.getInstanceForApplication(getContext());
        beaconManager.getBeaconParsers().add(new BeaconParser().setBeaconLayout(iBeaconLayout));
        beaconManager.bind(this);

        //beaconManager.setForegroundScanPeriod(1500l);

        // beaconManager.setForegroundBetweenScanPeriod(5000l);
        beaconManager.setRegionExitPeriod(1000l);
        Log.d("exitTag",""+beaconManager.getForegroundScanPeriod());
        Log.d("exitTag",""+beaconManager.getForegroundBetweenScanPeriod());
       /* beaconManager.setBackgroundScanPeriod(1100l);
        beaconManager.setBackgroundBetweenScanPeriod(10000l);*/

    }
    public void startBeaconMonitoring(){
        Log.d("startbeaconmonitoring","startBeaconMonitoring Called");
        try{
            rangingMessageRaised=false;
            exitMessageRaised=false;
            entryMessageRaised=false;

            //beaconRegion = new Region("MyBeacons ", null,null,null);
            //   beaconRegion = new Region("E2C Beacon", Identifier.parse("e2c56DB5-DFFB-48D2-B060-D0F5A71096E0"),Identifier.parse("0"),Identifier.parse("5"));
            // beaconRegion = new Region("E2C Beacon", Identifier.parse("d897f728-20e6-4780-b90c-bbbc79f6d429"),Identifier.parse("38045"),Identifier.parse("9566"));
            beaconRegion = new Region("E2C Beacon", Identifier.parse("c3a55f0f-5ba6-4b01-a1c2-e251fd0e1ed4"),Identifier.parse("64273"),Identifier.parse("64807"));

            beaconManager.startMonitoringBeaconsInRegion(beaconRegion);
            //beaconManager.startRangingBeaconsInRegion(beaconRegion);
            //arkaplan için rangingbootstrap
        }catch (RemoteException e){
            e.printStackTrace();
        }
    }

    void getPermission(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // Android M Permission check
            if (getContext().checkSelfPermission(Manifest.permission.BLUETOOTH) != PackageManager.PERMISSION_GRANTED) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("This app needs location access");
                builder.setMessage("Please grant location access so this app can detect beacons in the background.");
                builder.setPositiveButton(android.R.string.ok, null);
                builder.setOnDismissListener(new DialogInterface.OnDismissListener() {

                    @TargetApi(23)
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        requestPermissions(new String[]{Manifest.permission.BLUETOOTH},
                                123);
                    }
                });
                builder.show();
            }
        }
    }

    long startTime = 0;
    String uuid,major,minor;
    @Override
    public void onBeaconServiceConnect() {
        Log.d(TAG,"onBeaconServiceConnect Called");
        beaconManager.addMonitorNotifier(new MonitorNotifier() {
            @Override
            public void didEnterRegion(Region region) {
                if (!entryMessageRaised) {
                    //startTime = System.currentTimeMillis();
              //      showAlert("Enter Region", "Beacon detected! Distance:" + " UUID: " + region.getId1() + "/" + region.getId2() + "/" + region.getId3());

                    try {
                        //beaconManager.startRangingBeaconsInRegion(new Region("myRangingUniqueId", null, null, null));
                        //beaconRegion = new Region("E2C Beacon", Identifier.parse("e2c56DB5-DFFB-48D2-B060-D0F5A71096E0"),Identifier.parse("0"),Identifier.parse("5"));
                        beaconRegion = new Region("E2C Beacon", Identifier.parse("d897f728-20e6-4780-b90c-bbbc79f6d429"),Identifier.parse("38045"),Identifier.parse("9566"));//tx0
                        // beaconRegion = new Region("E2C Beacon", Identifier.parse("c3a55f0f-5ba6-4b01-a1c2-e251fd0e1ed4"),Identifier.parse("64273"),Identifier.parse("64807"));
                        beaconManager.startRangingBeaconsInRegion(beaconRegion);

                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    beaconManager.addRangeNotifier(new RangeNotifier() {
                        @Override
                        public void didRangeBeaconsInRegion(Collection<Beacon> beacons, Region region) {
                            if (!rangingMessageRaised && beacons != null && !beacons.isEmpty()) {
                                for (Beacon beacon : beacons) {
                                    if (beacon.getRssi() > -60.0) {
                                        uuid = beacon.getId1().toString();
                                        major = beacon.getId1().toString();
                                        minor = beacon.getId1().toString();
                                        if(startTime!=0) {
                                            long endTime = System.currentTimeMillis();
                                            long estimatedTime = endTime - startTime; // Geçen süreyi milisaniye cinsinden elde ediyoruz
                                            double seconds = ((double) estimatedTime / 1000);
                                            Toast.makeText(getContext(), "Süre" + seconds, Toast.LENGTH_SHORT).show();

                                        }
                                       // showAlert("Ranging after Enter Region", "Beacon detected! RSSI:" + beacon.getRssi() + " UUID: " + beacon.getId1() + "/" + beacon.getId2() + "/" + beacon.getId3());
                                        // rangingMessageRaised=true;
                                    }
                                    else{
                                        if(startTime==0)
                                            startTime = System.currentTimeMillis();
                                      //  else
                                            //showAlert("Ranging after Exit Region (<-60)", "Beacon detected! Distance:" + beacon.getRssi() + " UUID: " + beacon.getId1() + "/" + beacon.getId2() + "/" + beacon.getId3());;

                                    }
                                    //rangingMessageRaised=true;
                                }

                            }
                        }
                    });
                    //  showAlert("didEntryRegion","Entering region:"+region.getUniqueId()+"Beacon detected "+ region.getId1()+"/"+region.getId2()+"/"+region.getId3());
                    entryMessageRaised = true;
                }
            }

            @Override
            public void didExitRegion(Region region) {
                if (!exitMessageRaised){
                    /*long endTime = System.currentTimeMillis();
                    long estimatedTime = endTime - startTime; // Geçen süreyi milisaniye cinsinden elde ediyoruz
                    double seconds = ((double)estimatedTime/1000)-1;
                    Toast.makeText(MainActivity.this, "Süre"+seconds, Toast.LENGTH_SHORT).show();*/
            //        showAlert("didExitRegion","Exiting region:"+region.getUniqueId()+"Beacon detected "+ region.getId1()+"/"+region.getId2()+"/"+region.getId3());
                    exitMessageRaised=true;
                }
            }

            @Override
            public void didDetermineStateForRegion(int i, Region region) {

            }
        });

    }

    @Override
    public Context getApplicationContext() {
        return getActivity().getApplicationContext();

    }

    @Override
    public void unbindService(ServiceConnection serviceConnection) {
        getActivity().unbindService(serviceConnection);

    }

    @Override
    public boolean bindService(Intent intent, ServiceConnection serviceConnection, int i) {
        return getActivity().bindService(intent, serviceConnection, i);

    }


}