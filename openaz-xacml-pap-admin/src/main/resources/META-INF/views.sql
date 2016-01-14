
CREATE VIEW match_functions AS SELECT D.ID AS id, D.SHORT_NAME AS shortname, D.XACML_ID AS xacmlid,	D.RETURN_DATATYPE AS return_datatype, D.IS_BAG_RETURN AS is_bag_return,	D.ARG_LB AS arg_lb,	D.ARG_UB AS arg_ub,	A1.IS_BAG AS arg1_isbag, A1.DATATYPE_ID AS arg1_datatype, A2.IS_BAG AS arg2_isbag,	A2.DATATYPE_ID AS arg2_datatype FROM (FunctionDefinition D left join FunctionArguments A1 on (A1.FUNCTION_ID = D.ID and A1.ARG_INDEX = 1) left join FunctionArguments A2 on (A2.FUNCTION_ID = D.ID and A2.ARG_INDEX = 2)) where (D.ARG_LB = 2 and D.ARG_UB = 2 and D.RETURN_DATATYPE = 18 and A1.IS_BAG = 0) order by D.SHORT_NAME;

CREATE VIEW function_flattener AS SELECT D.ID AS id, D.SHORT_NAME AS shortname, D.RETURN_DATATYPE AS return_datatype, D.IS_BAG_RETURN AS is_bag_return, D.IS_HIGHER_ORDER AS is_higher_order, D.ARG_LB AS arg_lb, D.ARG_UB AS arg_ub, A1.IS_BAG AS arg1_isbag, A1.DATATYPE_ID AS arg1_datatype, A2.IS_BAG AS arg2_isbag, A2.DATATYPE_ID AS arg2_datatype, A3.IS_BAG AS arg3_isbag, A3.DATATYPE_ID AS arg3_datatype FROM (FunctionDefinition D left join FunctionArguments A1 ON (A1.FUNCTION_ID = D.ID and A1.ARG_INDEX = 1) left join FunctionArguments A2 ON (A2.FUNCTION_ID = D.ID and A2.ARG_INDEX = 2) LEFT JOIN FunctionArguments A3 ON (A3.FUNCTION_ID = D.ID and A3.ARG_INDEX = 3)) ORDER BY D.ID;

CREATE VIEW higherorder_bag_functions AS SELECT * FROM function_flattener WHERE is_higher_order = 1 AND is_bag_return = 1 AND return_datatype=18 AND arg_lb=2 AND arg_ub=2 AND arg1_isbag = 1 AND (arg2_isbag = 1 OR arg2_isbag IS NULL);
