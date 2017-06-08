# HorizontalPicker
A simple, customizable and easy to use picker where centre view is scaled up

[![License Apache](https://img.shields.io/badge/license-Apache-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0)
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-HorizontalPicker-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/5849)

![Demo screen 1](https://raw.githubusercontent.com/adityagohad/HorizontalPicker/master/art/picker_demo.gif)

## QuickStart ##
### Include the Gradle dependency ###

```java
dependencies {
    compile 'com.github.adityagohad:HorizontalPicker:1.0.1'
}
```
Don't forget to add following to build.gradle(Project:{your_project_name>})

```
allprojects {
    repositories {
        jcenter()
        maven { url "https://jitpack.io" }
    }
}
```

## How to use ##
Your picker will be recyclerView and this lib is just a custom layout manager
to initialize the PickerLayoutManager and set the properties

```java
PickerLayoutManager pickerLayoutManager = new PickerLayoutManager(this, PickerLayoutManager.HORIZONTAL, false);
pickerLayoutManager.setChangeAlpha(true);
pickerLayoutManager.setScaleDownBy(0.99f);
pickerLayoutManager.setScaleDownDistance(0.8f);
```
To make it snap to centre use ```SnapHelper```

```java
SnapHelper snapHelper = new LinearSnapHelper();
snapHelper.attachToRecyclerView(recyclerView);
```
set layout manager of your recyclerVew

```java
recyclerView.setLayoutManager(pickerLayoutManager);
```

To get selected view use ```onScrollStopListener```

```java
pickerLayoutManager.setOnScrollStopListener(new PickerLayoutManager.onScrollStopListener() {
    @Override
        public void selectedView(View view) {
            //Do your thing
        }
    });
}
```
## Don't forget this ##
To its core it is a recycler view with custom layout manager so if you forget to add ``` clipToPadding="false" ``` you will never be able to select first and last few elemets(values/views).
<br>So always add paddingLeft and paddingRight like shown below. 
```
    <android.support.v7.widget.RecyclerView
        android:id="@+id/rv"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_centerHorizontal="true"
        android:layout_centerVertical="true"
        android:clipToPadding="false"
        android:paddingLeft="183dp"
        android:paddingRight="183dp" />
```
