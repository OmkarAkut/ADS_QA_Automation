$scriptDir = @ScriptDir
$fileName = "IPFC22WDICD10.txt"
$dynamicFilePath = $scriptDir & "\" & $fileName
ControlFocus("Open","","Edit1")
ControlSetText("Open","","Edit1",$dynamicFilePath)
ControlClick("Open","","Button2")