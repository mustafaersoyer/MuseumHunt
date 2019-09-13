package com.example.museumhunt.UI.tour;

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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.example.museumhunt.Adapters.SingleArtifactsAdapter;
import com.example.museumhunt.Model.Artifacts;
import com.example.museumhunt.R;
import com.me.textfab.FloatingActionButton;

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

    protected static final String TAG = "MonitoringActivity";
    public String uuid = "1231",
            major = "343", minor = "22";
    SingleArtifactsAdapter adapter;
    //RecyclerView recyclerView;
    long startTime = 0;
    boolean enter = true;
    Context context;
    private TourViewModel tourViewModel;
    private Boolean entryMessageRaised = false;
    private Boolean exitMessageRaised = false;
    private Boolean rangingMessageRaised = false;
    private BeaconManager beaconManager = null;
    private Region beaconRegion;
    ImageView imageView;
    TextView textView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_tour, container, false);

        FloatingActionButton textFab = root.findViewById(R.id.fab);


     /*   recyclerView = root.findViewById(R.id.recyclerViewTour);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));*/

        imageView = root.findViewById(R.id.imageViewTour);

        tourViewModel =
                ViewModelProviders.of(getActivity()).get(TourViewModel.class);
        beaconSet();
        getPermission();

        textFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startBeaconMonitoring();
            }
        });

        context = inflater.getContext();

        return root;
    }

    void beaconSet() {
        String iBeaconLayout = "m:2-3=0215,i:4-19,i:20-21,i:22-23,p:24-24";
        beaconManager = BeaconManager.getInstanceForApplication(getContext());
        beaconManager.getBeaconParsers().add(new BeaconParser().setBeaconLayout(iBeaconLayout));
        beaconManager.bind(this);

        BeaconManager.setRegionExitPeriod(1000l);

    }

    public void startBeaconMonitoring() {
        Log.d("startbeaconmonitoring", "startBeaconMonitoring Called");
        try {
            rangingMessageRaised = false;
            exitMessageRaised = false;
            entryMessageRaised = false;

            //beaconRegion = new Region("MyBeacons ", null,null,null);
            // beaconRegion = new Region("E2C Beacon", Identifier.parse("e2c56DB5-DFFB-48D2-B060-D0F5A71096E0"),Identifier.parse("0"),Identifier.parse("5"));
            // beaconRegion = new Region("E2C Beacon", Identifier.parse("d897f728-20e6-4780-b90c-bbbc79f6d429"),Identifier.parse("38045"),Identifier.parse("9566"));
            beaconRegion = new Region("E2C Beacon", Identifier.parse("8861d9cb-39e6-4b90-8308-4eb5da394cc4"), Identifier.parse("19"), Identifier.parse("5"));

            beaconManager.startMonitoringBeaconsInRegion(beaconRegion);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    void getPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (getContext().checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("This app needs location access");
                builder.setMessage("Please grant location access so this app can detect beacons in the background.");
                builder.setPositiveButton(android.R.string.ok, null);
                builder.setOnDismissListener(new DialogInterface.OnDismissListener() {

                    @TargetApi(23)
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        requestPermissions(new String[]{Manifest.permission.BLUETOOTH, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, 123);
                    }
                });
                builder.show();
            }
        }
    }

    @Override
    public void onBeaconServiceConnect() {
        Log.d(TAG, "onBeaconServiceConnect Called");
        beaconManager.addMonitorNotifier(new MonitorNotifier() {
            @Override
            public void didEnterRegion(Region region) {
                if (!entryMessageRaised) {
                    Toast.makeText(context, "Enter Region", Toast.LENGTH_SHORT).show();

                    try {
                        //beaconManager.startRangingBeaconsInRegion(new Region("myRangingUniqueId", null, null, null));
                        //beaconRegion = new Region("E2C Beacon", Identifier.parse("e2c56DB5-DFFB-48D2-B060-D0F5A71096E0"),Identifier.parse("0"),Identifier.parse("5"));
                        //beaconRegion = new Region("E2C Beacon", Identifier.parse("d897f728-20e6-4780-b90c-bbbc79f6d429"),Identifier.parse("38045"),Identifier.parse("9566"));//tx0
                        // beaconRegion = new Region("E2C Beacon", Identifier.parse("c3a55f0f-5ba6-4b01-a1c2-e251fd0e1ed4"),Identifier.parse("64273"),Identifier.parse("64807"));
                        beaconRegion = new Region("E2C Beacon", Identifier.parse("8861d9cb-39e6-4b90-8308-4eb5da394cc4"), Identifier.parse("19"), Identifier.parse("5"));
                        Toast.makeText(getContext(), "Enter Region TRY", Toast.LENGTH_SHORT).show();

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
                                        major = beacon.getId2().toString();
                                        minor = beacon.getId3().toString();

                                       // recyclerView.setVisibility(View.VISIBLE);
                                        if (enter) {
                                            startTime = System.currentTimeMillis();
                                            tourViewModel.getArtifacts(uuid, major, minor).observe(getActivity(), new Observer<Artifacts>() {
                                                @Override
                                                public void onChanged(Artifacts artifacts) {
                                                    /*adapter = new SingleArtifactsAdapter(getContext(), artifacts);
                                                    recyclerView.setAdapter(adapter);*/
                                                    Glide.with(getContext())
                                                            .load("http://192.168.10.197:49994"+artifacts.getMainImageURL())
                                                            .into(imageView);
                                                    textView.setText(artifacts.getName());

                                                }
                                            });
                                            enter = false;
                                        }
                                        if (startTime != 0) {
                                            long endTime = System.currentTimeMillis();
                                            long estimatedTime = endTime - startTime; // Geçen süreyi milisaniye cinsinden elde ediyoruz
                                            double seconds = ((double) estimatedTime / 1000);
                                            Toast.makeText(context, "Süre" + seconds, Toast.LENGTH_SHORT).show();

                                        }
                                    } else {
                                       // recyclerView.setVisibility(View.GONE);
                                        enter = true;
                                    }
                                }
                            }
                        }
                    });
                    entryMessageRaised = true;
                }
            }

            @Override
            public void didExitRegion(Region region) {
                if (!exitMessageRaised) exitMessageRaised = true;
            }

            @Override
            public void didDetermineStateForRegion(int i, Region region) { }
        });

    }

    @Override
    public Context getApplicationContext() {
        if (getActivity() != null)
            return getActivity().getApplicationContext();
        else
            return null;

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