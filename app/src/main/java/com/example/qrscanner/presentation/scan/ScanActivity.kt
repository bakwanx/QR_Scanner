package com.example.qrscanner.presentation.scan

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Size
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import com.example.qrscanner.QrCodeAnalyzer
import com.example.qrscanner.R
import com.example.qrscanner.presentation.TransactionViewModel
import com.example.qrscanner.presentation.MainActivity
import com.example.qrscanner.presentation.pay.PayActivity
import com.example.qrscanner.presentation.pay.model.PayModel
import com.example.qrscanner.ui.theme.QRScannerTheme
import com.example.qrscanner.util.scanText
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ScanActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QRScannerTheme {
                val transactionViewModel by viewModels<TransactionViewModel>()
                val balance by transactionViewModel.getBalance.observeAsState()

                var code by remember {
                    mutableStateOf("")
                }
                if(code.isNotEmpty()){
                    val payModel : PayModel = scanText(code)
                    val result = transactionViewModel.doTransaction(payModel.nominal)
                    if(!result){
                        Toast.makeText(this, "Saldo tidak mencukupi", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, MainActivity::class.java)
                        this.startActivity(intent)

                    } else {
                        transactionViewModel.addHistory(payModel)
                        val intent = Intent(this, PayActivity::class.java)
                        intent.putExtra(PayActivity.KEY_PAY, code)
                        this.startActivity(intent)
                    }
                    finish()

                }
                val context = LocalContext.current
                val lifecycleOwner = LocalLifecycleOwner.current
                val cameraProviderFuture = remember {
                    ProcessCameraProvider.getInstance(context)
                }
                var hasCameraPermission by remember {
                    mutableStateOf(
                        ContextCompat.checkSelfPermission(
                            context,
                            Manifest.permission.CAMERA
                        ) == PackageManager.PERMISSION_GRANTED
                    )
                }
                val launcher = rememberLauncherForActivityResult(
                    contract = ActivityResultContracts.RequestPermission(),
                    onResult = { granted ->
                        hasCameraPermission = granted
                    }
                )
                LaunchedEffect(key1 = true) {
                    launcher.launch(Manifest.permission.CAMERA)
                }

                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    if (hasCameraPermission) {
                        AndroidView(
                            factory = { context ->
                                val previewView = PreviewView(context)
                                val preview = Preview.Builder().build()
                                val selector = CameraSelector.Builder()
                                    .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                                    .build()
                                preview.setSurfaceProvider(previewView.surfaceProvider)
                                val imageAnalysis = ImageAnalysis.Builder()
                                    .setTargetResolution(
                                        Size(
                                            previewView.width,
                                            previewView.height
                                        )
                                    )
                                    .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                                    .build()
                                imageAnalysis.setAnalyzer(
                                    ContextCompat.getMainExecutor(context),
                                    QrCodeAnalyzer { result ->
                                        code = result
                                    }
                                )
                                try {
                                    cameraProviderFuture.get().bindToLifecycle(
                                        lifecycleOwner,
                                        selector,
                                        preview,
                                        imageAnalysis
                                    )
                                } catch (e: Exception) {
                                    e.printStackTrace()
                                }
                                previewView
                            },
                            modifier = Modifier.weight(1f)
                        )

                        Row(
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier.fillMaxWidth()  .padding(32.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .height(70.dp)
                                    .width(100.dp)
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_qris),
                                    contentDescription = "",
                                )
                            }
                        }


                    }
                }

                // A surface container using the 'background' color from the theme
                //                Surface(
                //                    modifier = Modifier.fillMaxSize(),
                //                    color = MaterialTheme.colorScheme.background
                //                ) {
                //                    Greeting(name = "ehe")
                //                }
            }


        }
    }


}