/**
 This JS File loads the DOM with a DataTables Object via JQuery API
 For Loading JavaScript Sourced Data and JQuery Datatables take a look at:

 1. https://datatables.net/examples/data_sources/js_array.html
 2. https://datatables.net/forums/discussion/35360/cant-get-json-data-to-show-using-js-object-variable-works-with-ajax

 Get our userList object from Thymeleaf Model
 https://www.thymeleaf.org/doc/articles/thymeleaf3migration.html
 https://github.com/jmiguelsamper/thymeleaf3-template-modes-example
 *
 */
//
// var modaleSectionTable = null;

$(function(){

    // console.log(userList)


    var modaleSectionTable = $("#table1").DataTable({
                "Paginate": true,
                "bLengthChange": false,
                "bFilter": true,
                "bscrollX": true,
                "bscrollY": true,
                "bSort": true,
                "bInfo": false,
                "bAutoWidth": false,
                "colReorder": true,
                "dom": 'Bfrtip',
                "buttons": [
                    'pdf',
                        {
                            extend: 'columnToggle',
                            columns: '.firstColumn'
                        },
                                            {
                            extend: 'columnToggle',
                            columns: '.secondColumn'
                        },
                 ],
                "ajax":'',
                "data": balanceList,
                "dataSrc": '',
                "columns": [
                    { data: "transactionDate" },
                    { data: "transactionType" },
                    { data: "amount" },
                    { data: "balance" },
                ],
                "columnDefs": [
                    {targets:'_all',className : 'dt-head-center'},
                ]
            });

        });