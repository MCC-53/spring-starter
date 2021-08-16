function addRequestHeader() {
    $(document).ajaxSend((evt, req) => {
        req.setRequestHeader('Authorization', 'Basic ' + getToken());
    });
}