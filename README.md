# HorizontalPicker
A simple, customizable and easy to use picker where centre view is scaled up

## QuickStart ##
### Include the Gradle dependency ###

```java
dependencies {
    compile 'com.github.adityagohad:HorizontalPicker:1.0.0'
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
```
