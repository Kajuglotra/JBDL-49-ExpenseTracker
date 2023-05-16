package org.gfg.expenseTracker.controller;

import org.gfg.expenseTracker.model.TxnFilterOperators;
import org.gfg.expenseTracker.model.TxnFilterType;
import org.gfg.expenseTracker.request.CreateTxnRequest;
import org.gfg.expenseTracker.response.AnalyticalResponse;
import org.gfg.expenseTracker.response.CreateTxnResponse;
import org.gfg.expenseTracker.response.GenericResponse;
import org.gfg.expenseTracker.response.TxnSearchResponse;
import org.gfg.expenseTracker.service.TxnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/expenseTracker")
public class TxnController {

    @Autowired
    private TxnService txnService;

    @PostMapping("/addUserTxn")
    public GenericResponse<CreateTxnResponse> addUserTxn(@Valid @RequestBody CreateTxnRequest createTxnRequest){
        CreateTxnResponse createTxnResponse = txnService.addUserTxn(createTxnRequest);
        GenericResponse genericResponse = GenericResponse.builder().
                code(HttpStatus.OK.value()).
                message("user details has been saved").
                statusCode(0).data(createTxnResponse)
                .build();
        return genericResponse;

    }
    // i dont have any business logic -> repository
   // custom method -> filter all the things
    // cost if cost == something , cost >= something , cost <= something
    // expenseType expenseType == this
    // expenseDate expenseDate == this

    @GetMapping("/fetchTxn")
    public GenericResponse<List<TxnSearchResponse>> fetchUserTxnDetails(@RequestParam("filter")TxnFilterType txnFilterType,
                                                                        @RequestParam("operator")TxnFilterOperators operators,
                                                                        @RequestParam("values") String value) throws ParseException {

        // i have to create an array from comma separated
        String[] values = value.split(",");
        List<TxnSearchResponse> listOfTxnSearchResp = txnService.fetchUserTxnDetails(txnFilterType,operators, values);
        GenericResponse genericResponse = GenericResponse.builder().
                code(HttpStatus.OK.value()).
                message("user details has been saved").
                statusCode(0).data(listOfTxnSearchResp)
                .build();
        return genericResponse;
    }

    // api -> one day expense, 7 day expense, 15 day, 30 days

    @GetMapping("/fetchCalculatedResults")
    public GenericResponse<AnalyticalResponse> fetchCalculatedResponse(@RequestParam("email") String email){
        AnalyticalResponse analyticalResponse = txnService.fetchCalculatedResponse(email);
        GenericResponse genericResponse = GenericResponse.builder().
                code(HttpStatus.OK.value()).
                message("user details has been saved").
                statusCode(0).data(analyticalResponse)
                .build();
        return genericResponse;

    }


}
