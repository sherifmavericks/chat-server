package com.openchat.imservice.internal.push;

public final class ProvisioningProtos {
  private ProvisioningProtos() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface ProvisionEnvelopeOrBuilder
      extends com.google.protobuf.MessageOrBuilder {

    
    boolean hasPublicKey();
    
    com.google.protobuf.ByteString getPublicKey();

    
    boolean hasBody();
    
    com.google.protobuf.ByteString getBody();
  }
  
  public static final class ProvisionEnvelope extends
      com.google.protobuf.GeneratedMessage
      implements ProvisionEnvelopeOrBuilder {
    private ProvisionEnvelope(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private ProvisionEnvelope(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final ProvisionEnvelope defaultInstance;
    public static ProvisionEnvelope getDefaultInstance() {
      return defaultInstance;
    }

    public ProvisionEnvelope getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private ProvisionEnvelope(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      initFields();
      int mutable_bitField0_ = 0;
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            default: {
              if (!parseUnknownField(input, unknownFields,
                                     extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
            case 10: {
              bitField0_ |= 0x00000001;
              publicKey_ = input.readBytes();
              break;
            }
            case 18: {
              bitField0_ |= 0x00000002;
              body_ = input.readBytes();
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e.getMessage()).setUnfinishedMessage(this);
      } finally {
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.openchat.imservice.internal.push.ProvisioningProtos.internal_static_openchatservice_ProvisionEnvelope_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.openchat.imservice.internal.push.ProvisioningProtos.internal_static_openchatservice_ProvisionEnvelope_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.openchat.imservice.internal.push.ProvisioningProtos.ProvisionEnvelope.class, com.openchat.imservice.internal.push.ProvisioningProtos.ProvisionEnvelope.Builder.class);
    }

    public static com.google.protobuf.Parser<ProvisionEnvelope> PARSER =
        new com.google.protobuf.AbstractParser<ProvisionEnvelope>() {
      public ProvisionEnvelope parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new ProvisionEnvelope(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<ProvisionEnvelope> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    public static final int PUBLICKEY_FIELD_NUMBER = 1;
    private com.google.protobuf.ByteString publicKey_;
    
    public boolean hasPublicKey() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    
    public com.google.protobuf.ByteString getPublicKey() {
      return publicKey_;
    }

    public static final int BODY_FIELD_NUMBER = 2;
    private com.google.protobuf.ByteString body_;
    
    public boolean hasBody() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    
    public com.google.protobuf.ByteString getBody() {
      return body_;
    }

    private void initFields() {
      publicKey_ = com.google.protobuf.ByteString.EMPTY;
      body_ = com.google.protobuf.ByteString.EMPTY;
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized != -1) return isInitialized == 1;

      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      getSerializedSize();
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        output.writeBytes(1, publicKey_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        output.writeBytes(2, body_);
      }
      getUnknownFields().writeTo(output);
    }

    private int memoizedSerializedSize = -1;
    public int getSerializedSize() {
      int size = memoizedSerializedSize;
      if (size != -1) return size;

      size = 0;
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(1, publicKey_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(2, body_);
      }
      size += getUnknownFields().getSerializedSize();
      memoizedSerializedSize = size;
      return size;
    }

    private static final long serialVersionUID = 0L;
    @java.lang.Override
    protected java.lang.Object writeReplace()
        throws java.io.ObjectStreamException {
      return super.writeReplace();
    }

    public static com.openchat.imservice.internal.push.ProvisioningProtos.ProvisionEnvelope parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.openchat.imservice.internal.push.ProvisioningProtos.ProvisionEnvelope parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.openchat.imservice.internal.push.ProvisioningProtos.ProvisionEnvelope parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.openchat.imservice.internal.push.ProvisioningProtos.ProvisionEnvelope parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.openchat.imservice.internal.push.ProvisioningProtos.ProvisionEnvelope parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.openchat.imservice.internal.push.ProvisioningProtos.ProvisionEnvelope parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static com.openchat.imservice.internal.push.ProvisioningProtos.ProvisionEnvelope parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static com.openchat.imservice.internal.push.ProvisioningProtos.ProvisionEnvelope parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static com.openchat.imservice.internal.push.ProvisioningProtos.ProvisionEnvelope parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.openchat.imservice.internal.push.ProvisioningProtos.ProvisionEnvelope parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(com.openchat.imservice.internal.push.ProvisioningProtos.ProvisionEnvelope prototype) {
      return newBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() { return newBuilder(this); }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessage.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder>
       implements com.openchat.imservice.internal.push.ProvisioningProtos.ProvisionEnvelopeOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.openchat.imservice.internal.push.ProvisioningProtos.internal_static_openchatservice_ProvisionEnvelope_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.openchat.imservice.internal.push.ProvisioningProtos.internal_static_openchatservice_ProvisionEnvelope_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.openchat.imservice.internal.push.ProvisioningProtos.ProvisionEnvelope.class, com.openchat.imservice.internal.push.ProvisioningProtos.ProvisionEnvelope.Builder.class);
      }

      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessage.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
        }
      }
      private static Builder create() {
        return new Builder();
      }

      public Builder clear() {
        super.clear();
        publicKey_ = com.google.protobuf.ByteString.EMPTY;
        bitField0_ = (bitField0_ & ~0x00000001);
        body_ = com.google.protobuf.ByteString.EMPTY;
        bitField0_ = (bitField0_ & ~0x00000002);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.openchat.imservice.internal.push.ProvisioningProtos.internal_static_openchatservice_ProvisionEnvelope_descriptor;
      }

      public com.openchat.imservice.internal.push.ProvisioningProtos.ProvisionEnvelope getDefaultInstanceForType() {
        return com.openchat.imservice.internal.push.ProvisioningProtos.ProvisionEnvelope.getDefaultInstance();
      }

      public com.openchat.imservice.internal.push.ProvisioningProtos.ProvisionEnvelope build() {
        com.openchat.imservice.internal.push.ProvisioningProtos.ProvisionEnvelope result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.openchat.imservice.internal.push.ProvisioningProtos.ProvisionEnvelope buildPartial() {
        com.openchat.imservice.internal.push.ProvisioningProtos.ProvisionEnvelope result = new com.openchat.imservice.internal.push.ProvisioningProtos.ProvisionEnvelope(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.publicKey_ = publicKey_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.body_ = body_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.openchat.imservice.internal.push.ProvisioningProtos.ProvisionEnvelope) {
          return mergeFrom((com.openchat.imservice.internal.push.ProvisioningProtos.ProvisionEnvelope)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.openchat.imservice.internal.push.ProvisioningProtos.ProvisionEnvelope other) {
        if (other == com.openchat.imservice.internal.push.ProvisioningProtos.ProvisionEnvelope.getDefaultInstance()) return this;
        if (other.hasPublicKey()) {
          setPublicKey(other.getPublicKey());
        }
        if (other.hasBody()) {
          setBody(other.getBody());
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }

      public final boolean isInitialized() {
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.openchat.imservice.internal.push.ProvisioningProtos.ProvisionEnvelope parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.openchat.imservice.internal.push.ProvisioningProtos.ProvisionEnvelope) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private com.google.protobuf.ByteString publicKey_ = com.google.protobuf.ByteString.EMPTY;
      
      public boolean hasPublicKey() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      
      public com.google.protobuf.ByteString getPublicKey() {
        return publicKey_;
      }
      
      public Builder setPublicKey(com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000001;
        publicKey_ = value;
        onChanged();
        return this;
      }
      
      public Builder clearPublicKey() {
        bitField0_ = (bitField0_ & ~0x00000001);
        publicKey_ = getDefaultInstance().getPublicKey();
        onChanged();
        return this;
      }

      private com.google.protobuf.ByteString body_ = com.google.protobuf.ByteString.EMPTY;
      
      public boolean hasBody() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      
      public com.google.protobuf.ByteString getBody() {
        return body_;
      }
      
      public Builder setBody(com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
        body_ = value;
        onChanged();
        return this;
      }
      
      public Builder clearBody() {
        bitField0_ = (bitField0_ & ~0x00000002);
        body_ = getDefaultInstance().getBody();
        onChanged();
        return this;
      }

    }

    static {
      defaultInstance = new ProvisionEnvelope(true);
      defaultInstance.initFields();
    }

  }

  public interface ProvisionMessageOrBuilder
      extends com.google.protobuf.MessageOrBuilder {

    
    boolean hasIdentityKeyPublic();
    
    com.google.protobuf.ByteString getIdentityKeyPublic();

    
    boolean hasIdentityKeyPrivate();
    
    com.google.protobuf.ByteString getIdentityKeyPrivate();

    
    boolean hasNumber();
    
    java.lang.String getNumber();
    
    com.google.protobuf.ByteString
        getNumberBytes();

    
    boolean hasProvisioningCode();
    
    java.lang.String getProvisioningCode();
    
    com.google.protobuf.ByteString
        getProvisioningCodeBytes();

    
    boolean hasUserAgent();
    
    java.lang.String getUserAgent();
    
    com.google.protobuf.ByteString
        getUserAgentBytes();

    
    boolean hasProfileKey();
    
    com.google.protobuf.ByteString getProfileKey();

    
    boolean hasReadReceipts();
    
    boolean getReadReceipts();
  }
  
  public static final class ProvisionMessage extends
      com.google.protobuf.GeneratedMessage
      implements ProvisionMessageOrBuilder {
    private ProvisionMessage(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private ProvisionMessage(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final ProvisionMessage defaultInstance;
    public static ProvisionMessage getDefaultInstance() {
      return defaultInstance;
    }

    public ProvisionMessage getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private ProvisionMessage(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      initFields();
      int mutable_bitField0_ = 0;
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            default: {
              if (!parseUnknownField(input, unknownFields,
                                     extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
            case 10: {
              bitField0_ |= 0x00000001;
              identityKeyPublic_ = input.readBytes();
              break;
            }
            case 18: {
              bitField0_ |= 0x00000002;
              identityKeyPrivate_ = input.readBytes();
              break;
            }
            case 26: {
              bitField0_ |= 0x00000004;
              number_ = input.readBytes();
              break;
            }
            case 34: {
              bitField0_ |= 0x00000008;
              provisioningCode_ = input.readBytes();
              break;
            }
            case 42: {
              bitField0_ |= 0x00000010;
              userAgent_ = input.readBytes();
              break;
            }
            case 50: {
              bitField0_ |= 0x00000020;
              profileKey_ = input.readBytes();
              break;
            }
            case 56: {
              bitField0_ |= 0x00000040;
              readReceipts_ = input.readBool();
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e.getMessage()).setUnfinishedMessage(this);
      } finally {
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.openchat.imservice.internal.push.ProvisioningProtos.internal_static_openchatservice_ProvisionMessage_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.openchat.imservice.internal.push.ProvisioningProtos.internal_static_openchatservice_ProvisionMessage_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.openchat.imservice.internal.push.ProvisioningProtos.ProvisionMessage.class, com.openchat.imservice.internal.push.ProvisioningProtos.ProvisionMessage.Builder.class);
    }

    public static com.google.protobuf.Parser<ProvisionMessage> PARSER =
        new com.google.protobuf.AbstractParser<ProvisionMessage>() {
      public ProvisionMessage parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new ProvisionMessage(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<ProvisionMessage> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    public static final int IDENTITYKEYPUBLIC_FIELD_NUMBER = 1;
    private com.google.protobuf.ByteString identityKeyPublic_;
    
    public boolean hasIdentityKeyPublic() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    
    public com.google.protobuf.ByteString getIdentityKeyPublic() {
      return identityKeyPublic_;
    }

    public static final int IDENTITYKEYPRIVATE_FIELD_NUMBER = 2;
    private com.google.protobuf.ByteString identityKeyPrivate_;
    
    public boolean hasIdentityKeyPrivate() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    
    public com.google.protobuf.ByteString getIdentityKeyPrivate() {
      return identityKeyPrivate_;
    }

    public static final int NUMBER_FIELD_NUMBER = 3;
    private java.lang.Object number_;
    
    public boolean hasNumber() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    
    public java.lang.String getNumber() {
      java.lang.Object ref = number_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          number_ = s;
        }
        return s;
      }
    }
    
    public com.google.protobuf.ByteString
        getNumberBytes() {
      java.lang.Object ref = number_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        number_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int PROVISIONINGCODE_FIELD_NUMBER = 4;
    private java.lang.Object provisioningCode_;
    
    public boolean hasProvisioningCode() {
      return ((bitField0_ & 0x00000008) == 0x00000008);
    }
    
    public java.lang.String getProvisioningCode() {
      java.lang.Object ref = provisioningCode_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          provisioningCode_ = s;
        }
        return s;
      }
    }
    
    public com.google.protobuf.ByteString
        getProvisioningCodeBytes() {
      java.lang.Object ref = provisioningCode_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        provisioningCode_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int USERAGENT_FIELD_NUMBER = 5;
    private java.lang.Object userAgent_;
    
    public boolean hasUserAgent() {
      return ((bitField0_ & 0x00000010) == 0x00000010);
    }
    
    public java.lang.String getUserAgent() {
      java.lang.Object ref = userAgent_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          userAgent_ = s;
        }
        return s;
      }
    }
    
    public com.google.protobuf.ByteString
        getUserAgentBytes() {
      java.lang.Object ref = userAgent_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        userAgent_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int PROFILEKEY_FIELD_NUMBER = 6;
    private com.google.protobuf.ByteString profileKey_;
    
    public boolean hasProfileKey() {
      return ((bitField0_ & 0x00000020) == 0x00000020);
    }
    
    public com.google.protobuf.ByteString getProfileKey() {
      return profileKey_;
    }

    public static final int READRECEIPTS_FIELD_NUMBER = 7;
    private boolean readReceipts_;
    
    public boolean hasReadReceipts() {
      return ((bitField0_ & 0x00000040) == 0x00000040);
    }
    
    public boolean getReadReceipts() {
      return readReceipts_;
    }

    private void initFields() {
      identityKeyPublic_ = com.google.protobuf.ByteString.EMPTY;
      identityKeyPrivate_ = com.google.protobuf.ByteString.EMPTY;
      number_ = "";
      provisioningCode_ = "";
      userAgent_ = "";
      profileKey_ = com.google.protobuf.ByteString.EMPTY;
      readReceipts_ = false;
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized != -1) return isInitialized == 1;

      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      getSerializedSize();
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        output.writeBytes(1, identityKeyPublic_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        output.writeBytes(2, identityKeyPrivate_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        output.writeBytes(3, getNumberBytes());
      }
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        output.writeBytes(4, getProvisioningCodeBytes());
      }
      if (((bitField0_ & 0x00000010) == 0x00000010)) {
        output.writeBytes(5, getUserAgentBytes());
      }
      if (((bitField0_ & 0x00000020) == 0x00000020)) {
        output.writeBytes(6, profileKey_);
      }
      if (((bitField0_ & 0x00000040) == 0x00000040)) {
        output.writeBool(7, readReceipts_);
      }
      getUnknownFields().writeTo(output);
    }

    private int memoizedSerializedSize = -1;
    public int getSerializedSize() {
      int size = memoizedSerializedSize;
      if (size != -1) return size;

      size = 0;
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(1, identityKeyPublic_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(2, identityKeyPrivate_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(3, getNumberBytes());
      }
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(4, getProvisioningCodeBytes());
      }
      if (((bitField0_ & 0x00000010) == 0x00000010)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(5, getUserAgentBytes());
      }
      if (((bitField0_ & 0x00000020) == 0x00000020)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(6, profileKey_);
      }
      if (((bitField0_ & 0x00000040) == 0x00000040)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBoolSize(7, readReceipts_);
      }
      size += getUnknownFields().getSerializedSize();
      memoizedSerializedSize = size;
      return size;
    }

    private static final long serialVersionUID = 0L;
    @java.lang.Override
    protected java.lang.Object writeReplace()
        throws java.io.ObjectStreamException {
      return super.writeReplace();
    }

    public static com.openchat.imservice.internal.push.ProvisioningProtos.ProvisionMessage parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.openchat.imservice.internal.push.ProvisioningProtos.ProvisionMessage parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.openchat.imservice.internal.push.ProvisioningProtos.ProvisionMessage parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.openchat.imservice.internal.push.ProvisioningProtos.ProvisionMessage parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.openchat.imservice.internal.push.ProvisioningProtos.ProvisionMessage parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.openchat.imservice.internal.push.ProvisioningProtos.ProvisionMessage parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static com.openchat.imservice.internal.push.ProvisioningProtos.ProvisionMessage parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static com.openchat.imservice.internal.push.ProvisioningProtos.ProvisionMessage parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static com.openchat.imservice.internal.push.ProvisioningProtos.ProvisionMessage parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.openchat.imservice.internal.push.ProvisioningProtos.ProvisionMessage parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(com.openchat.imservice.internal.push.ProvisioningProtos.ProvisionMessage prototype) {
      return newBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() { return newBuilder(this); }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessage.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder>
       implements com.openchat.imservice.internal.push.ProvisioningProtos.ProvisionMessageOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.openchat.imservice.internal.push.ProvisioningProtos.internal_static_openchatservice_ProvisionMessage_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.openchat.imservice.internal.push.ProvisioningProtos.internal_static_openchatservice_ProvisionMessage_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.openchat.imservice.internal.push.ProvisioningProtos.ProvisionMessage.class, com.openchat.imservice.internal.push.ProvisioningProtos.ProvisionMessage.Builder.class);
      }

      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessage.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
        }
      }
      private static Builder create() {
        return new Builder();
      }

      public Builder clear() {
        super.clear();
        identityKeyPublic_ = com.google.protobuf.ByteString.EMPTY;
        bitField0_ = (bitField0_ & ~0x00000001);
        identityKeyPrivate_ = com.google.protobuf.ByteString.EMPTY;
        bitField0_ = (bitField0_ & ~0x00000002);
        number_ = "";
        bitField0_ = (bitField0_ & ~0x00000004);
        provisioningCode_ = "";
        bitField0_ = (bitField0_ & ~0x00000008);
        userAgent_ = "";
        bitField0_ = (bitField0_ & ~0x00000010);
        profileKey_ = com.google.protobuf.ByteString.EMPTY;
        bitField0_ = (bitField0_ & ~0x00000020);
        readReceipts_ = false;
        bitField0_ = (bitField0_ & ~0x00000040);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.openchat.imservice.internal.push.ProvisioningProtos.internal_static_openchatservice_ProvisionMessage_descriptor;
      }

      public com.openchat.imservice.internal.push.ProvisioningProtos.ProvisionMessage getDefaultInstanceForType() {
        return com.openchat.imservice.internal.push.ProvisioningProtos.ProvisionMessage.getDefaultInstance();
      }

      public com.openchat.imservice.internal.push.ProvisioningProtos.ProvisionMessage build() {
        com.openchat.imservice.internal.push.ProvisioningProtos.ProvisionMessage result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.openchat.imservice.internal.push.ProvisioningProtos.ProvisionMessage buildPartial() {
        com.openchat.imservice.internal.push.ProvisioningProtos.ProvisionMessage result = new com.openchat.imservice.internal.push.ProvisioningProtos.ProvisionMessage(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.identityKeyPublic_ = identityKeyPublic_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.identityKeyPrivate_ = identityKeyPrivate_;
        if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
          to_bitField0_ |= 0x00000004;
        }
        result.number_ = number_;
        if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
          to_bitField0_ |= 0x00000008;
        }
        result.provisioningCode_ = provisioningCode_;
        if (((from_bitField0_ & 0x00000010) == 0x00000010)) {
          to_bitField0_ |= 0x00000010;
        }
        result.userAgent_ = userAgent_;
        if (((from_bitField0_ & 0x00000020) == 0x00000020)) {
          to_bitField0_ |= 0x00000020;
        }
        result.profileKey_ = profileKey_;
        if (((from_bitField0_ & 0x00000040) == 0x00000040)) {
          to_bitField0_ |= 0x00000040;
        }
        result.readReceipts_ = readReceipts_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.openchat.imservice.internal.push.ProvisioningProtos.ProvisionMessage) {
          return mergeFrom((com.openchat.imservice.internal.push.ProvisioningProtos.ProvisionMessage)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.openchat.imservice.internal.push.ProvisioningProtos.ProvisionMessage other) {
        if (other == com.openchat.imservice.internal.push.ProvisioningProtos.ProvisionMessage.getDefaultInstance()) return this;
        if (other.hasIdentityKeyPublic()) {
          setIdentityKeyPublic(other.getIdentityKeyPublic());
        }
        if (other.hasIdentityKeyPrivate()) {
          setIdentityKeyPrivate(other.getIdentityKeyPrivate());
        }
        if (other.hasNumber()) {
          bitField0_ |= 0x00000004;
          number_ = other.number_;
          onChanged();
        }
        if (other.hasProvisioningCode()) {
          bitField0_ |= 0x00000008;
          provisioningCode_ = other.provisioningCode_;
          onChanged();
        }
        if (other.hasUserAgent()) {
          bitField0_ |= 0x00000010;
          userAgent_ = other.userAgent_;
          onChanged();
        }
        if (other.hasProfileKey()) {
          setProfileKey(other.getProfileKey());
        }
        if (other.hasReadReceipts()) {
          setReadReceipts(other.getReadReceipts());
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }

      public final boolean isInitialized() {
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.openchat.imservice.internal.push.ProvisioningProtos.ProvisionMessage parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.openchat.imservice.internal.push.ProvisioningProtos.ProvisionMessage) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private com.google.protobuf.ByteString identityKeyPublic_ = com.google.protobuf.ByteString.EMPTY;
      
      public boolean hasIdentityKeyPublic() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      
      public com.google.protobuf.ByteString getIdentityKeyPublic() {
        return identityKeyPublic_;
      }
      
      public Builder setIdentityKeyPublic(com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000001;
        identityKeyPublic_ = value;
        onChanged();
        return this;
      }
      
      public Builder clearIdentityKeyPublic() {
        bitField0_ = (bitField0_ & ~0x00000001);
        identityKeyPublic_ = getDefaultInstance().getIdentityKeyPublic();
        onChanged();
        return this;
      }

      private com.google.protobuf.ByteString identityKeyPrivate_ = com.google.protobuf.ByteString.EMPTY;
      
      public boolean hasIdentityKeyPrivate() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      
      public com.google.protobuf.ByteString getIdentityKeyPrivate() {
        return identityKeyPrivate_;
      }
      
      public Builder setIdentityKeyPrivate(com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
        identityKeyPrivate_ = value;
        onChanged();
        return this;
      }
      
      public Builder clearIdentityKeyPrivate() {
        bitField0_ = (bitField0_ & ~0x00000002);
        identityKeyPrivate_ = getDefaultInstance().getIdentityKeyPrivate();
        onChanged();
        return this;
      }

      private java.lang.Object number_ = "";
      
      public boolean hasNumber() {
        return ((bitField0_ & 0x00000004) == 0x00000004);
      }
      
      public java.lang.String getNumber() {
        java.lang.Object ref = number_;
        if (!(ref instanceof java.lang.String)) {
          java.lang.String s = ((com.google.protobuf.ByteString) ref)
              .toStringUtf8();
          number_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      
      public com.google.protobuf.ByteString
          getNumberBytes() {
        java.lang.Object ref = number_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          number_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      
      public Builder setNumber(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000004;
        number_ = value;
        onChanged();
        return this;
      }
      
      public Builder clearNumber() {
        bitField0_ = (bitField0_ & ~0x00000004);
        number_ = getDefaultInstance().getNumber();
        onChanged();
        return this;
      }
      
      public Builder setNumberBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000004;
        number_ = value;
        onChanged();
        return this;
      }

      private java.lang.Object provisioningCode_ = "";
      
      public boolean hasProvisioningCode() {
        return ((bitField0_ & 0x00000008) == 0x00000008);
      }
      
      public java.lang.String getProvisioningCode() {
        java.lang.Object ref = provisioningCode_;
        if (!(ref instanceof java.lang.String)) {
          java.lang.String s = ((com.google.protobuf.ByteString) ref)
              .toStringUtf8();
          provisioningCode_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      
      public com.google.protobuf.ByteString
          getProvisioningCodeBytes() {
        java.lang.Object ref = provisioningCode_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          provisioningCode_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      
      public Builder setProvisioningCode(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000008;
        provisioningCode_ = value;
        onChanged();
        return this;
      }
      
      public Builder clearProvisioningCode() {
        bitField0_ = (bitField0_ & ~0x00000008);
        provisioningCode_ = getDefaultInstance().getProvisioningCode();
        onChanged();
        return this;
      }
      
      public Builder setProvisioningCodeBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000008;
        provisioningCode_ = value;
        onChanged();
        return this;
      }

      private java.lang.Object userAgent_ = "";
      
      public boolean hasUserAgent() {
        return ((bitField0_ & 0x00000010) == 0x00000010);
      }
      
      public java.lang.String getUserAgent() {
        java.lang.Object ref = userAgent_;
        if (!(ref instanceof java.lang.String)) {
          java.lang.String s = ((com.google.protobuf.ByteString) ref)
              .toStringUtf8();
          userAgent_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      
      public com.google.protobuf.ByteString
          getUserAgentBytes() {
        java.lang.Object ref = userAgent_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          userAgent_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      
      public Builder setUserAgent(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000010;
        userAgent_ = value;
        onChanged();
        return this;
      }
      
      public Builder clearUserAgent() {
        bitField0_ = (bitField0_ & ~0x00000010);
        userAgent_ = getDefaultInstance().getUserAgent();
        onChanged();
        return this;
      }
      
      public Builder setUserAgentBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000010;
        userAgent_ = value;
        onChanged();
        return this;
      }

      private com.google.protobuf.ByteString profileKey_ = com.google.protobuf.ByteString.EMPTY;
      
      public boolean hasProfileKey() {
        return ((bitField0_ & 0x00000020) == 0x00000020);
      }
      
      public com.google.protobuf.ByteString getProfileKey() {
        return profileKey_;
      }
      
      public Builder setProfileKey(com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000020;
        profileKey_ = value;
        onChanged();
        return this;
      }
      
      public Builder clearProfileKey() {
        bitField0_ = (bitField0_ & ~0x00000020);
        profileKey_ = getDefaultInstance().getProfileKey();
        onChanged();
        return this;
      }

      private boolean readReceipts_ ;
      
      public boolean hasReadReceipts() {
        return ((bitField0_ & 0x00000040) == 0x00000040);
      }
      
      public boolean getReadReceipts() {
        return readReceipts_;
      }
      
      public Builder setReadReceipts(boolean value) {
        bitField0_ |= 0x00000040;
        readReceipts_ = value;
        onChanged();
        return this;
      }
      
      public Builder clearReadReceipts() {
        bitField0_ = (bitField0_ & ~0x00000040);
        readReceipts_ = false;
        onChanged();
        return this;
      }

    }

    static {
      defaultInstance = new ProvisionMessage(true);
      defaultInstance.initFields();
    }

  }

  private static com.google.protobuf.Descriptors.Descriptor
    internal_static_openchatservice_ProvisionEnvelope_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_openchatservice_ProvisionEnvelope_fieldAccessorTable;
  private static com.google.protobuf.Descriptors.Descriptor
    internal_static_openchatservice_ProvisionMessage_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_openchatservice_ProvisionMessage_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {""};
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
      new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
        public com.google.protobuf.ExtensionRegistry assignDescriptors(
            com.google.protobuf.Descriptors.FileDescriptor root) {
          descriptor = root;
          internal_static_openchatservice_ProvisionEnvelope_descriptor =
            getDescriptor().getMessageTypes().get(0);
          internal_static_openchatservice_ProvisionEnvelope_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessage.FieldAccessorTable(
              internal_static_openchatservice_ProvisionEnvelope_descriptor,
              new java.lang.String[] { "PublicKey", "Body", });
          internal_static_openchatservice_ProvisionMessage_descriptor =
            getDescriptor().getMessageTypes().get(1);
          internal_static_openchatservice_ProvisionMessage_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessage.FieldAccessorTable(
              internal_static_openchatservice_ProvisionMessage_descriptor,
              new java.lang.String[] { "IdentityKeyPublic", "IdentityKeyPrivate", "Number", "ProvisioningCode", "UserAgent", "ProfileKey", "ReadReceipts", });
          return null;
        }
      };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
  }

}
