<!DOCTYPE HTML>
<html manifest="">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=10, user-scalable=yes">
    <link rel="stylesheet" href="/resources/ext-theme-neptune/theme-neptune-all.css">
    <link rel="stylesheet" href="app.css">

    <script type="text/javascript" src="/ext-all.js"></script>
    <script type="text/javascript" src="/locale-zh_CN.js"></script>
    <script type="text/javascript" src="/theme-neptune.js"></script>

    <title>音乐下载助手</title>
</head>
<body>
    <script type="text/javascript">
        Ext.Loader.setConfig({
            enabled: true
        });
        Ext.Loader.setPath('Music', '/app');
        Ext.onReady(function () {
            window.app = Ext.create('Music.App');
        });
    </script>
</body>
</html>
