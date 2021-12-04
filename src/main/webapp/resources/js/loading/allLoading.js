var loading = new loadingGear('gear'); 
loading.createLoadingGear(); 
window.onbeforeunload = function () { loading.on(); }