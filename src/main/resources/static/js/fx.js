$(function() {
    // 初期表示（一覧検索）
    $.ajax({
        url: '/search',
        type: 'post',
        data: {},
    }).done (function(data) {
        $('#trade-list-box').html(data);
    });

    // searchボタン
    $('#searchBtn').on('click', function() {
        // 一覧検索
        $.ajax({
            url: '/search',
            type: 'post',
            data: { fromDate: $('#fromDate').val(), toDate: $('#toDate').val() },
        }).done (function(data) {
            if ($(data).attr('id') == 'errors') {
                // エラーの場合
                var errors = $(data).find('span').map(function() { return $(this).html() }).get();
                alert(errors.join('\n'));
            } else {
                // 正常の場合
                $('#trade-list-box').html(data);
            }
        });
    });

    // addボタン
    $('#addBtn').on('click', function() {
        // モーダルを新規表示
        $.ajax({
            url: '/new',
            type: 'post',
            data: {},
        }).done (function(data) {
            $('body').append(data);
        });
    });

    // deleteボタン
    $('#deleteBtn').on('click', function() {
        var deleteIds = $('.deleteId:checked').map(function() {
            return $(this).val();
        }).get();
        $.ajax({
            url: '/delete',
            type: 'post',
            data: { deleteIds: deleteIds }
        }).done (function(msg) {
            alert(msg);
            $('#searchBtn').trigger('click');
        });
    });

    // IDクリック
    $(document).on('click', '.tradeId', function(e) {
        e.preventDefault();
        // モーダルを編集表示
        $.ajax({
            url: '/edit',
            type: 'post',
            data: { id: $(this).html() },
        }).done (function(data) {
            $('body').append(data);
        });
    });

    // モーダル saveボタン
    $(document).on('click', '#saveBtn', function() {
        // モーダルを新規表示
        $.ajax({
            url: '/save',
            type: 'post',
            data: {
                id: $('#tradeId').html(),
                tradingDate: $('#tradingDate').val(),
                settlementDate: $('#settlementDate').val(),
                currencyPairId: $('#currencyPair').val(),
                tradeType: $('[name="tradeType"]:checked').val(),
                quantity: $('#quantity').val(),
                entryPrice: $('#entryPrice').val(),
                exitPrice: $('#exitPrice').val(),
                stopLoss: $('#stopLoss').val(),
                profit: $('#profit').val(),
                comment: $('#comment').val()
            },
        }).done (function(data) {
            if (data.errors.length) {
            // エラーの場合
                alert(data.errors.join('\n'));
            } else {
            // 正常の場合
                alert(data.message);
                // モーダルを閉じる。
                $('#modalContainer').remove();
                $('#searchBtn').trigger('click');
            }
        });
    });
    // モーダル枠外のクリック
    $(document).on('click', '.overlay', function() {
        // モーダルを閉じる。
        $('#modalContainer').remove();
    });
})