package com.openchat.secureim.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.annotations.VisibleForTesting;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

public class PreKeyStateV2 {

  @JsonProperty
  @NotNull
  @Valid
  private List<PreKeyV2> preKeys;

  @JsonProperty
  @NotNull
  @Valid
  private SignedPreKey signedPreKey;

  @JsonProperty
  @NotNull
  @Valid
  private PreKeyV2 lastResortKey;

  @JsonProperty
  @NotEmpty
  private String identityKey;

  public PreKeyStateV2() {}

  @VisibleForTesting
  public PreKeyStateV2(String identityKey, SignedPreKey signedPreKey,
                       List<PreKeyV2> keys, PreKeyV2 lastResortKey)
  {
    this.identityKey   = identityKey;
    this.signedPreKey  = signedPreKey;
    this.preKeys       = keys;
    this.lastResortKey = lastResortKey;
  }

  public List<PreKeyV2> getPreKeys() {
    return preKeys;
  }

  public SignedPreKey getSignedPreKey() {
    return signedPreKey;
  }

  public String getIdentityKey() {
    return identityKey;
  }

  public PreKeyV2 getLastResortKey() {
    return lastResortKey;
  }
}