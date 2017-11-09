Ext.define('Music.MusicGridPanel', {
    extend: 'Ext.grid.Panel',
    
    store: Ext.create('Ext.data.JsonStore', {
        autoLoad: false,
        proxy: {
            type: 'ajax',
            url: '/search',
            extraParams: {
                keywords: ''
            },
            reader: {
                type: 'json'
            }
        }
    }),

    columns: [{
        text: '歌名',
        flex: 4
    }, {
        text: '演唱',
        flex: 1
    }, {
        text: '专辑',
        flex: 2
    }],

    initComponent: function(){
        var me = this;
        me.input = Ext.create('Ext.form.field.Text');
        Ext.apply(me, {
            tbar: [me.input, {
                text: '搜索',
                handler: me.search,
                scope: me
            }],
            bbar: Ext.create('Ext.toolbar.Paging', {
                store: me.store
            })
        });
        me.callParent(arguments);
    },

    search: function () {
        var me = this;
        var keywords = me.input.getValue();
        me.getStore().getProxy().setExtraParam('keywords', keywords);
        me.getStore().reload();
    }
});