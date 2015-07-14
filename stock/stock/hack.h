// some of these are defined in MinGw's specstrings.h, but not all...

#define uCLSSPEC     void*
#define QUERYCONTEXT void*
struct IXMLElement
{
	void* unused;
};

#define __in
#define __in_opt
#define __out
#define __out_opt
#define __inout_opt
#define __inout
#define __deref_inout
#define __out_ecount_part(c, p)
#define __in_bcount_opt(c)
#define __out_bcount_part(c, p)
#define __reserved
#define __deref_out
#define __deref_out_opt
#define __out_ecount(c)

#define __out_bcount_part_opt(c, p)
#define __in_range(a, b)

#define __RPC__in
#define __RPC__in_opt
#define __RPC__out
#define __RPC__out_opt
#define __RPC__inout
#define __RPC__inout_opt
#define __RPC_unique_pointer
#define __RPC__deref_out_opt
#define __RPC__deref_out_ecount_full_opt(c)
#define __RPC__in_ecount_full(c)
#define __RPC__out_ecount_full(c)
#define __RPC__in_xcount(c)
#define __RPC__inout_xcount(c)
#define __RPC__inout_ecount_full(c)
#define __RPC__out_ecount(c) 