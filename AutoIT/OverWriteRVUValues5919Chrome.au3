$scriptDir = @ScriptDir
$fileName = "ADS1309PreConditionsRVUImport.txt"
$dynamicFilePath = $scriptDir & "\" & $fileName
ControlFocus("Open","","Edit1")
ControlSetText("Open","","Edit1",$dynamicFilePath)
ControlClick("Open","","Button1")