package com.example.smscallcapture.ui.fragments;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0002J\u0010\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J$\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\u0010 \u001a\u0004\u0018\u00010!H\u0016J\b\u0010\"\u001a\u00020\u0014H\u0016J\u001a\u0010#\u001a\u00020\u00142\u0006\u0010$\u001a\u00020\u001b2\b\u0010 \u001a\u0004\u0018\u00010!H\u0016J\u000e\u0010%\u001a\u00020\u00142\u0006\u0010&\u001a\u00020\u0016J\b\u0010\'\u001a\u00020\u0014H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\u00020\u00048BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\r\u001a\u00020\u000e8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u000f\u0010\u0010\u00a8\u0006("}, d2 = {"Lcom/example/smscallcapture/ui/fragments/CallsFragment;", "Landroidx/fragment/app/Fragment;", "()V", "_binding", "Lcom/example/smscallcapture/databinding/FragmentCallsBinding;", "adapter", "Lcom/example/smscallcapture/ui/adapters/CallsAdapter;", "binding", "getBinding", "()Lcom/example/smscallcapture/databinding/FragmentCallsBinding;", "fullList", "", "Lcom/example/smscallcapture/data/models/CallEntity;", "viewModel", "Lcom/example/smscallcapture/ui/viewmodels/CallViewModel;", "getViewModel", "()Lcom/example/smscallcapture/ui/viewmodels/CallViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "filterList", "", "query", "", "importCallsFrom", "startMillis", "", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "onViewCreated", "view", "setStatusFilter", "status", "showDatePicker", "app_debug"})
public final class CallsFragment extends androidx.fragment.app.Fragment {
    @org.jetbrains.annotations.Nullable()
    private com.example.smscallcapture.databinding.FragmentCallsBinding _binding;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy viewModel$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.smscallcapture.ui.adapters.CallsAdapter adapter = null;
    @org.jetbrains.annotations.NotNull()
    private java.util.List<com.example.smscallcapture.data.models.CallEntity> fullList;
    
    public CallsFragment() {
        super();
    }
    
    private final com.example.smscallcapture.databinding.FragmentCallsBinding getBinding() {
        return null;
    }
    
    private final com.example.smscallcapture.ui.viewmodels.CallViewModel getViewModel() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable()
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void filterList(java.lang.String query) {
    }
    
    public final void setStatusFilter(@org.jetbrains.annotations.NotNull()
    java.lang.String status) {
    }
    
    private final void showDatePicker() {
    }
    
    private final void importCallsFrom(long startMillis) {
    }
    
    @java.lang.Override()
    public void onDestroyView() {
    }
}